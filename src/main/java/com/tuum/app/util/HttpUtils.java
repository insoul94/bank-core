package com.tuum.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.app.constant.Currency;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.BalanceDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.entity.Account;
import com.tuum.app.entity.Balance;
import com.tuum.app.entity.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

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
