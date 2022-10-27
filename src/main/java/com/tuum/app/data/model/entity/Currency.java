package com.tuum.app.data.model.entity;

public enum Currency {
    EUR, GBP, SEK, USD;

    public static String[] getAllAsStringArray() {
        String[] arr = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            arr[i] = values()[i].name();
        }
        return arr;
    }
}
