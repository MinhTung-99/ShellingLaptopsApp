package util;

public class PriceUtil {
	
	public static String setupPrice(String takePrice){
        int count = 0;
        String setupPriceStr = "";
        String str = takePrice;
        for(int i = takePrice.length()-1; i >= 0; i--){
            setupPriceStr += str.charAt(i);
            count++;
            if(count == 3){
                setupPriceStr += ".";
                count = 0;
            }
        }

        return new StringBuilder(setupPriceStr).reverse().toString();
    }
	
}
