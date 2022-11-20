package com.bank.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtils {

    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(String.format(
                    "Error while writing object to JSON. \nType: %s, \nError: %s",
                    obj.getClass().getName(), e.getMessage()));
        }
        return json;
    }

    public static <T> T fromJson(String str, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        try {
            obj = mapper.readValue(str, type);
        } catch (JsonProcessingException e) {
            log.error(String.format(
                    "Error while reading object from JSON. \nString: %s \nType: %s, \nError: %s",
                    str, type.getName(), e.getMessage()));
        }
        return obj;
    }







}
