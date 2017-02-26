package com.topoffers.topoffers.common.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Utils {
    public static String convertDoublePriceToStringPriceWithTag(double price) {
        return Utils.convertDoublePriceToStringPriceWithTag(price, "Price");
    }

    public static String convertDoublePriceToStringPriceWithTag(double price, String pricePrefix) {
        String productPriceAsString = String.valueOf(price);
        return String.format("%s: %s", pricePrefix, productPriceAsString);
    }

    public static String buildDetailsString(String prefix, String information) {
        return String.format("%s: %s", prefix, information);
    }

    public static String getOrderStatusColor(String status) {
        if (Objects.equals(status, "pending")) {
            return "#FF6A00";
        } else if (Objects.equals(status, "send")) {
            return "#0094FF";
        } else if (Objects.equals(status, "rejected")) {
            return "#A50000";
        } else if (Objects.equals(status, "received")) {
            return "#1D7C2F";
        } else if (Objects.equals(status, "notReceived")) {
            return "#A50000";
        } else {
            return "#A50000";
        }
    }

    public static String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(date);
    }

    public static String convertDateToStringWithTime(Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(date);
    }
}
