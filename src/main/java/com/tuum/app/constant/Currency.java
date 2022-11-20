package com.tuum.app.constant;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Currency {
    EUR, GBP, SEK, USD;

    public static String[] getValuesAsStringArray() {
        String[] arr = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            arr[i] = values()[i].name();
        }
        return arr;
    }

    public static Set<Currency> valuesAsSet() {
        return Arrays.stream(values()).collect(Collectors.toSet());
    }
}
