package com.tuum.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class TestData {

    public final static String POST_ACCOUNT_REQUEST_JSON;
    public final static String POST_GET_ACCOUNT_RESPONSE_JSON;
    public final static String POST_TRANSACTION_REQUEST_JSON;
    public final static String POST_TRASACTION_RESPONSE_JSON;
    public final static String GET_TRASACTION_RESPONSE_JSON;

    public final static int ACCOUNT_ID = 1;
    public final static int CUSTOMER_ID = 22;
    public final static int TRANSACTION_ID = 333;
    public final static String AMOUNT = "1000.00";
    public final static String ZERO_AMOUNT = "0.00";
    public final static String COUNTRY = "Estonia";
    public final static String DESCRIPTION = "payout";

    public enum CURRENCY {
        EUR, SEK, GBP, USD;
    }

    public enum DIRECTION {
        IN, OUT
    }

    private TestData() {
    }


    static {
        JSONObject json;
        JSONObject balances;
        try {
            json = new JSONObject();
            json.put("customerId", CUSTOMER_ID);
            json.put("country", COUNTRY);
            json.put("currencies", Arrays.stream(CURRENCY.values()).map(Enum::name).toArray());
            POST_ACCOUNT_REQUEST_JSON = json.toString();

            json = new JSONObject();
            json.put("accountId", ACCOUNT_ID);
            json.put("customerId", CUSTOMER_ID);
            balances = new JSONObject();
            balances.put(CURRENCY.EUR.name(), ZERO_AMOUNT);
            balances.put(CURRENCY.SEK.name(), ZERO_AMOUNT);
            balances.put(CURRENCY.GBP.name(), ZERO_AMOUNT);
            balances.put(CURRENCY.USD.name(), ZERO_AMOUNT);
            json.put("balance", balances);
            POST_GET_ACCOUNT_RESPONSE_JSON = json.toString();

            json = new JSONObject();
            json.put("accountId", ACCOUNT_ID);
            json.put("amount", AMOUNT);
            json.put("currency", CURRENCY.EUR.name());
            json.put("direction", DIRECTION.IN.name());
            json.put("description", DESCRIPTION);
            POST_TRANSACTION_REQUEST_JSON = json.toString();

            json.put("transactionId", TRANSACTION_ID);
            GET_TRASACTION_RESPONSE_JSON = json.toString();

            balances = new JSONObject();
            balances.put(CURRENCY.EUR.name(), AMOUNT);
            json.put("balance", balances);
            POST_TRASACTION_RESPONSE_JSON = json.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
