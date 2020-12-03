package com.example.shellinglaptopapp.tensorflowlite.ml;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import com.google.common.base.Joiner;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.metadata.MetadataExtractor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class QaClient implements AutoCloseable {

    private static final String MODEL_PATH = "lite-mobilebert.tflite";
    private static final String DIC_PATH = "vocab.txt";

    private final Context context;
    private final Map<String, Integer> dic = new HashMap<>();
    private static final Joiner SPACE_JOINER = Joiner.on(" ");
    private FeatureConverter featureConverter;
    private Interpreter tflite;

    public QaClient(Context context) {
        this.context = context;
        this.featureConverter = new FeatureConverter(dic, true, 64, 384);
    }

    @WorkerThread
    public synchronized void loadModel() {
        try {
            ByteBuffer buffer = loadModelFile(this.context.getAssets());
            Interpreter.Options opt = new Interpreter.Options();
            opt.setNumThreads(4);
            loadDictionary(buffer);
            tflite = new Interpreter(buffer, opt);
        } catch (IOException ex) {

        }
    }

    @WorkerThread
    public synchronized void loadDictionary(ByteBuffer byteBuffer) {
        try {
            MetadataExtractor metadata = new MetadataExtractor(byteBuffer);
            loadDictionaryFile(metadata.getAssociatedFile(DIC_PATH));
        } catch (IOException ex) {
        }
    }

    /** Load tflite model from assets. */
    public MappedByteBuffer loadModelFile(AssetManager assetManager) throws IOException {
        try (AssetFileDescriptor fileDescriptor = assetManager.openFd(MODEL_PATH);
             FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor())) {
            FileChannel fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        }
    }

    /** Load dictionary from assets. */
    public void loadDictionaryFile(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int index = 0;
            while (reader.ready()) {
                String key = reader.readLine();
                dic.put(key, index++);
            }
        }
    }

    /**
     * Input: Original content and query for the QA task. Later converted to Feature by
     * FeatureConverter. Output: A String[] array of answers and a float[] array of corresponding
     * logits.
     */
    @WorkerThread
    public synchronized ArrayList<QaAnswer> predict(String query, String content) {
        Feature feature = featureConverter.convert(query, content);

        int[][] inputIds = new int[1][384];
        int[][] inputMask = new int[1][384];
        int[][] segmentIds = new int[1][384];
        float[][] startLogits = new float[1][384];
        float[][] endLogits = new float[1][384];

        for (int j = 0; j < 384; j++) {
            inputIds[0][j] = feature.inputIds[j];
            inputMask[0][j] = feature.inputMask[j];
            segmentIds[0][j] = feature.segmentIds[j];
        }
        Object[] inputs = {inputIds, inputMask, segmentIds};
        Map<Integer, Object> output = new HashMap<>();
        output.put(0, endLogits);
        output.put(1, startLogits);

        tflite.runForMultipleInputsOutputs(inputs, output);

        ArrayList<QaAnswer> answers = getBestAnswers(startLogits[0], endLogits[0], feature);
        Log.d("KMFGG", inputMask[0][0] + " = " + segmentIds[0][0]);
        return answers;
    }

    /** Find the Best N answers & logits from the logits array and input feature. */
    private synchronized ArrayList<QaAnswer> getBestAnswers(float[] startLogits, float[] endLogits, Feature feature) {
        // Model uses the closed interval [start, end] for indices.
        int[] startIndexes = getBestIndex(startLogits);
        int[] endIndexes = getBestIndex(endLogits);
        List<QaAnswer.Pos> origResults = new ArrayList<>();
        for (int start : startIndexes) {
            for (int end : endIndexes) {
                if (!feature.tokenToOrigMap.containsKey(start + 1)) {
                    continue;
                }
                if (!feature.tokenToOrigMap.containsKey(end + 1)) {
                    continue;
                }
                if (end < start) {
                    continue;
                }
                int length = end - start + 1;
                if (length > 32) {
                    continue;
                }
                origResults.add(new QaAnswer.Pos(start, end, startLogits[start] + endLogits[end]));
            }
        }

        Collections.sort(origResults);
        ArrayList<QaAnswer> answers = new ArrayList<>();
        for (int i = 0; i < origResults.size(); i++) {
            if (i >= 5) {
                break;
            }

            String convertedText;
            if (origResults.get(i).start > 0) {
                convertedText = convertBack(feature, origResults.get(i).start, origResults.get(i).end);
            } else {
                convertedText = "";
            }
            QaAnswer ans = new QaAnswer(convertedText, origResults.get(i));
            answers.add(ans);
        }
        return answers;
    }

    /** Get the n-best logits from a list of all the logits. */
    @WorkerThread
    private synchronized int[] getBestIndex(float[] logits) {
        List<QaAnswer.Pos> tmpList = new ArrayList<>();
        for (int i = 0; i < 384; i++) {
            tmpList.add(new QaAnswer.Pos(i, i, logits[i]));
        }
        Collections.sort(tmpList);

        int[] indexes = new int[5];
        for (int i = 0; i < 5; i++) {
            indexes[i] = tmpList.get(i).start;
        }
        return indexes;
    }

    /** Convert the answer back to original text form. */
    @WorkerThread
    private static String convertBack(Feature feature, int start, int end) {
        // Shifted index is: index of logits + offset.
        int shiftedStart = start + 1;
        int shiftedEnd = end + 1;
        int startIndex = feature.tokenToOrigMap.get(shiftedStart);
        int endIndex = feature.tokenToOrigMap.get(shiftedEnd);
        // end + 1 for the closed interval.
        String ans = SPACE_JOINER.join(feature.origTokens.subList(startIndex, endIndex + 1));
        return ans;
    }

    @Override
    public void close() { }
}
