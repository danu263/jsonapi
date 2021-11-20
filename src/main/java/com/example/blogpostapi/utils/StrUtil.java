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
                s.equalsIgnoreCase(HatchwaysEnums.SortBy.ID.getValue()) ||
                s.equalsIgnoreCase(HatchwaysEnums.SortBy.READS.getValue()) ||
                s.equalsIgnoreCase(HatchwaysEnums.SortBy.LIKES.getValue()) ||
                s.equalsIgnoreCase(HatchwaysEnums.SortBy.POPULARITY.getValue());
    }

    public static boolean directionIsValid(String s) {
        if (Strings.isNullOrEmpty(s)) return true;
        else return
                s.equalsIgnoreCase(HatchwaysEnums.Order.ASC.getValue()) ||
                s.equalsIgnoreCase(HatchwaysEnums.Order.DESC.getValue());
    }
}
