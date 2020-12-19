package com.shellinglaptop.utils;

public class PriceUtils {

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

        String s = new StringBuilder(setupPriceStr).reverse().toString();
        String priceStr = "";
        if(s.charAt(0) == '.') {
            for(int i = 1; i < s.length(); i++) {
                priceStr += s.charAt(i);
            }
        }else {
            return s;
        }

        return priceStr;
    }
}
