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

    public static AccountResponseDto parseAccountResponseDto(Account account) {
        // TODO Where to handle null?
        if (account == null) {
            return null;
        }
        return AccountResponseDto.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .balanceDtos(parseIntoBalanceDtos(account.getBalances())).build();
    }

    /**
     * @param accountRequestDto
     * @return Account with null 'id' field.
     */
    public static Account parseAccount(AccountRequestDto accountRequestDto) {
        if (accountRequestDto == null) {
            return null;
        }
        return Account.builder()
                .customerId(accountRequestDto.getCustomerId())
                .country(accountRequestDto.getCountry())
                .balances(parseBalances(accountRequestDto.getCurrencies())).build();
    }


    // TODO: add JavaDOcs!!!
    /**
     * @param tr Transaction
     * @return TransactionResponseDto with null 'balance' field.
     */
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
                .description(tr.getDescription()).build();
    }

    public static Set<Balance> parseBalances(Currency[] currencies) {
        if (currencies == null) {
            return new HashSet<>();
        }
        return parseBalances(new HashSet<>(Arrays.asList(currencies)));
    }

    public static Set<Balance> parseBalances(Set<Currency> currencies) {
        if (currencies == null || currencies.size() == 0) {
            return new HashSet<>();
        }
        Set<Balance> balances = new HashSet<>();
        for (Currency c : currencies) {
            balances.add(
                    Balance.builder()
                    .currency(c)
                    .amount(BigDecimal.ZERO).build());
        }
        return balances;
    }

    public static Set<BalanceDto> parseIntoBalanceDtos(Set<Balance> balances) {
        if (balances == null || balances.size() == 0) {
            return new HashSet<>();
        }
        Set<BalanceDto> balanceDtos = new HashSet<>();
        for (Balance b : balances) {
            balanceDtos.add(
                    BalanceDto.builder()
                            .currency(b.getCurrency())
                            .amount(b.getAmount()).build()
            );
        }
        return balanceDtos;
    }
}
