package com.tuum.app.testdata;

import com.tuum.app.data.model.entity.Currency;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestData {

    public final static JSONObject POST_ACCOUNT_REQUEST_JSON;
    public final static JSONObject POST_TRANSACTION_REQUEST_JSON;
    public final static JSONObject POST_TRASACTION_RESPONSE_JSON;

    public final static long ACCOUNT_ID = 1;
    public final static long CUSTOMER_ID = 22;
    public final static int TRANSACTION_ID = 333;
    public final static String AMOUNT = "1000.00";
    public final static String COUNTRY = "Estonia";
    public final static String DESCRIPTION = "payout";

    public enum DIRECTION {
        IN, OUT
    }

    private TestData() {
    }


    static {
        JSONObject json;
        JSONObject balance;
        try {
            json = new JSONObject();
            json.put("customerId", CUSTOMER_ID);
            json.put("country", COUNTRY);
            json.put("currencies", new JSONArray(Currency.getAllAsStringArray()));
            POST_ACCOUNT_REQUEST_JSON = json;

            json = new JSONObject();
            json.put("accountId", ACCOUNT_ID);
            json.put("amount", AMOUNT);
            json.put("currency", Currency.EUR.name());
            json.put("direction", DIRECTION.IN.name());
            json.put("description", DESCRIPTION);
            POST_TRANSACTION_REQUEST_JSON = json;

            json = new JSONObject(json.toString());
            balance = new JSONObject();
            balance.put(Currency.EUR.name(), AMOUNT);
            json.put("balance", balance);
            POST_TRASACTION_RESPONSE_JSON = json;


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
