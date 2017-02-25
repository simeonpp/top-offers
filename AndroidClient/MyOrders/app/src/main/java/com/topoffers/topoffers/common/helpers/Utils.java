package com.topoffers.topoffers.common.helpers;

public class Utils {
    public static String convertDoublePriceToStringPriceWithTag(double price) {
        return Utils.convertDoublePriceToStringPriceWithTag(price, "Price");
    }

    public static String convertDoublePriceToStringPriceWithTag(double price, String pricePrefix) {
        String productPriceAsString = String.valueOf(price);
        return String.format("%s: %s", pricePrefix, productPriceAsString);
    }
}
