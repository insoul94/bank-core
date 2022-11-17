package com.tuum.app.testutil;

import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import com.tuum.app.dto.*;
import com.tuum.app.entity.Account;
import com.tuum.app.entity.Balance;
import com.tuum.app.util.HttpUtils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.tuum.app.util.HttpUtils.*;

public class MockData {

    public final static long ACCOUNT_ID = getRandomLong();
    // Test only positive numbers
    public final static long CUSTOMER_ID = getRandomLong();
    public final static long TRANSACTION_ID = getRandomLong();
    public final static BigDecimal AMOUNT = BigDecimal.valueOf(getRandomLong());
    public final static String COUNTRY = getRandomCountry();
    public final static String DESCRIPTION = getRandomString(100);
    public final static Currency CURRENCY = getRandomCurrency();

    private MockData() {
    }

    public static long getRandomLong() {
        return new Random().nextLong(Long.SIZE - 1);
    }

    public static String getRandomString(int size) {
        byte[] array = new byte[size];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public static String getRandomCountry() {
        String[] countries = {"Estonia", "Sweden", "Norway", "Latvia", "Lithuania", "Poland", "Denmark"};
        return countries[new Random().nextInt(countries.length)];
    }

    public static Currency getRandomCurrency() {
        return Currency.values()[new Random().nextInt(4)];
    }

    public static Account mockAccount() {
        return Account.builder()
                .id(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .balances(HttpUtils.parseBalances(Currency.values())).build();
    }

    public static AccountRequestDto mockAccountRequestDto() {
        return AccountRequestDto.builder()
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .currencies(Currency.valuesAsSet()).build();
    }

    public static String mockAccountRequestDtoJson() {
        return toJson(mockAccountRequestDto());
    }

    public static AccountResponseDto mockAccountResponseDto() {
        return AccountResponseDto.builder()
                .id(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .balanceDtos(mockBalanceDtoSet()).build();
    }

    public static Set<BalanceDto> mockBalanceDtoSet() {
        Set<BalanceDto> balanceDtos = new HashSet<>();
        for (Currency cur : Currency.values()) {
            BalanceDto dto = new BalanceDto();
            dto.setCurrency(cur);
            dto.setAmount(BigDecimal.ZERO);
            balanceDtos.add(dto);
        }
        return balanceDtos;
    }

    public static TransactionRequestDto mockTransactionRequestDto() {
        return TransactionRequestDto.builder()
                .accountId(ACCOUNT_ID)
                .amount(AMOUNT)
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION).build();
    }

    public static TransactionResponseDto mockTransactionResponseDto() {
        return TransactionResponseDto.builder()
                .accountId(ACCOUNT_ID)
                .amount(AMOUNT)
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .balanceAfter(AMOUNT).build();
    }

    public static List<TransactionResponseDto> mockTransactionResponseDtoList() {
        List<TransactionResponseDto> list = new ArrayList<>();
        list.add(mockTransactionResponseDto());
        return list;
    }


}
