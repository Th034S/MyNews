package com.siadous.thomas.mynews.utils;

public class DateUtils {

    public static String configureFormatDate(String date) {
        String[] split = date.split("T");
        date = split[0];
        split = date.split("-");
        String[] year = split[0].split("0");
        date = split[2] + "/" + split[1] + "/" + year[1];
        return date;
    }

}
