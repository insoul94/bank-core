package com.tuum.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.app.constant.Currency;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.entity.Account;
import com.tuum.app.entity.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

@Slf4j
public class HttpUtils {

    public static String toJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Error while parsing object to JSON. Class: " + o.getClass().getName());
        }
        return json;
    }

    public static AccountResponseDto parseIntoAccountResponseDto(Account account) {
        // TODO Where to handle null?
        if (account == null) {
            return null;
        }
        return AccountResponseDto.builder()
                .accountId(account.getId())
                .customerId(account.getCustomerId())
                .balances(account.getBalances())
                .build();
    }

    public static Account parseIntoAccount(AccountRequestDto accountRequestDto) {
        if (accountRequestDto == null) {
            return null;
        }
        return Account.builder()
                .customerId(accountRequestDto.getCustomerId())
                .country(accountRequestDto.getCountry())
                .balances(parseIntoBalances(accountRequestDto.getCurrencies()))
                .build();
    }

    public static TransactionResponseDto parseIntoTransactionResponseDto(Transaction tr) {
        if (tr == null) {
            return null;
        }
        return TransactionResponseDto.builder()
                .id(tr.getId())
                .accountId(tr.getAccountId())
                .amount(tr.getAmount())
                .currency(tr.getCurrency())
                .direction(tr.getDirection())
                .description(tr.getDescription())
//                .balance()
                .build();
    }

    public static HashMap<Currency, BigDecimal> parseIntoBalances(Set<Currency> set) {
        return parseIntoBalances(set.toArray(new Currency[0]));
    }

    public static HashMap<Currency, BigDecimal> parseIntoBalances(Currency[] arr) {
        HashMap<Currency, BigDecimal> balances = new HashMap<>();
        if (arr == null) {
            return new HashMap<>();
        }
        for (Currency c : arr) {
            balances.put(c, BigDecimal.ZERO);
        }
        return balances;
    }
}
