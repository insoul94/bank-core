package com.bank.app.constant;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: should be used java.util.Currency instead?
public enum Currency {
    EUR, GBP, SEK, USD;

    public static String[] valuesAsStringArray() {
        String[] arr = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            arr[i] = values()[i].name();
        }
        return arr;
    }

    public static Set<String> valuesAsStringSet() {
        return Arrays.stream(valuesAsStringArray()).collect(Collectors.toSet());
    }
}
