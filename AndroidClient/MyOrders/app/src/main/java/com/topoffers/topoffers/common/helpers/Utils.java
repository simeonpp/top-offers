package com.topoffers.topoffers.common.helpers;

public class Utils {
    public static String convertDoublePriceToStringPriceWithTag(double price) {
        String productPriceAsString = String.valueOf(price);
        return  String.format("Price: %s", productPriceAsString);
    }
}
