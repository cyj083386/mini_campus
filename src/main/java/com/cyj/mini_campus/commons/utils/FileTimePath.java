package com.dokuny.mini_campus.commons.utils;

import java.time.LocalDateTime;

public class FileTimePath {


    public static String getDayPath(String separator) {
        LocalDateTime now = LocalDateTime.now();
        return separator + now.getYear() + separator + now.getMonthValue() + separator + now.getDayOfMonth() + separator;

    }

    public static String[] getAllPath(String separator) {
        LocalDateTime now = LocalDateTime.now();
        String[] paths = {
                separator + now.getYear() + separator,
                separator + now.getYear() + separator + now.getMonthValue() + separator,
                separator + now.getYear() + separator + now.getMonthValue() + separator + now.getDayOfMonth() + separator
        };
        return paths;
    }

}
