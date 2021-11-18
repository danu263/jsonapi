package com.example.blogpostapi.utils;

import com.google.common.base.Strings;

public class StrUtil {

    private static final String regex = ",";

    public static String[] getSubStringsSeparatedByComma(String s) {
        return s.split(regex);
    }

    public static boolean sortByIsValid(String s) {
        if (Strings.isNullOrEmpty(s)) return true;
        else return
                s.equalsIgnoreCase("id") ||
                s.equalsIgnoreCase("reads") ||
                s.equalsIgnoreCase("likes") ||
                s.equalsIgnoreCase("popularity");
    }

    public static boolean directionIsValid(String s) {
        if (Strings.isNullOrEmpty(s)) return true;
        else return
                s.equalsIgnoreCase("asc") ||
                s.equalsIgnoreCase("desc");
    }
}
