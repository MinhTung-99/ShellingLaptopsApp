package com.example.shellinglaptopapp.ui.bert

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.tensorflowlite.ml.QaAnswer
import com.example.shellinglaptopapp.tensorflowlite.ml.QaClient

@RequiresApi(Build.VERSION_CODES.KITKAT)
class BertViewModel(
    private val context: Context
) : ViewModel(){

    val messages = MutableLiveData<ArrayList<String>>()
    private val _messages = ArrayList<String>()
    val edittext = MutableLiveData<String>()
    val progress = MutableLiveData<Int>()
    private var text: String? = null
    private var qaClient: QaClient? = null
    init {
        qaClient = QaClient(context)
        qaClient!!.loadModel()
    }

    private val content = "Hello. My name shop is LAPTOP ABC . My phone number is 0987654321 and it's address is Ha Noi . " +
            "I have many laptops . I have the Surface Lap top 3 . I have the Surface Lap top 2 , I have the Dell Vostro 559070197465 ," +
            "I have the Dell XPS 13 9300 , I have the Laptop Asus ZenBook , I have the Laptop Asus VivoBook , I have the MacBook Air 2020 , I have the MacBook Pro 2020 ."

    fun btnSendOnClick(view: View){
        progress.postValue(0)
        _messages.add(text!!)
        answerQuestion(text!!, context)
    }

    var messagesTextWatcher : TextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            text = s.toString()
        }

        override fun afterTextChanged(s: Editable?) {}

    }

    private fun answerQuestion(question: String, context: Context) {
        if (question.trim().isEmpty()) {
            return
        }

        if (!question.trim().endsWith("?")) {
            question.trim().plus('?')
        }

        val answers: ArrayList<QaAnswer> = qaClient!!.predict(question, content)
        _messages.add(answers[0].text)

        messages.postValue(_messages)
    }
}