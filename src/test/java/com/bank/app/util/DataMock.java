package com.bank.app.util;

import com.bank.app.dto.*;
import com.bank.app.entity.Balance;
import com.bank.app.mapper.BalanceMapper;
import com.bank.app.constant.Currency;
import com.bank.app.constant.Direction;
import com.bank.app.entity.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.bank.app.util.HttpUtils.*;

public class DataMock {

    public final static long ACCOUNT_ID = getRandomLong();
    // Test only positive numbers
    public final static long CUSTOMER_ID = getRandomLong();
    public final static long TRANSACTION_ID = getRandomLong();
    public final static String AMOUNT = String.valueOf(getRandomAmount());
    public final static String COUNTRY = getRandomCountry();
    public final static String DESCRIPTION = getRandomString(100);
    public final static Currency CURRENCY = getRandomCurrency();

    private DataMock() {
    }

    public static BigDecimal getRandomAmount() {
        BigDecimal n = new BigDecimal(getRandomLong()).setScale(2, RoundingMode.HALF_EVEN);
        return n.divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);
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

    public static Balance mockBalance() {
        return Balance.builder()
                .account(mockAccount())
                .currency(CURRENCY)
                .amount(new BigDecimal(AMOUNT))
                .build();
    }
    public static Set<Balance> mockBalanceSet() {
        return BalanceMapper.toEntitySet(Currency.valuesAsSet(), mockAccount());
    }

    public static Set<BalanceDto> mockBalanceDtoSet() {
        return BalanceMapper.toDtoSet(mockBalanceSet());
    }

    public static Account mockAccount() {
        Account account = Account.builder()
                .id(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .build();
        account.setBalances(BalanceMapper.toEntitySet(Currency.valuesAsSet(), account));
        return account;
    }

    public static AccountRequestDto mockAccountRequestDto() {
        return AccountRequestDto.builder()
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .currencies(Currency.valuesAsSet())
                .build();
    }

    public static String mockAccountRequestDtoJson() {
        return toJson(mockAccountRequestDto());
    }

    public static String mockAccountRequestWithInvalidCurrencyJson() {
        return "{\"customer_id\":1, country:\"Estonia\", \"currencies\":[\"ABC\"]}";
    }

    public static AccountResponseDto mockAccountResponseDto() {
        return AccountResponseDto.builder()
                .id(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .balanceDtoSet(mockBalanceDtoSet())
                .build();
    }

    public static TransactionRequestDto mockTransactionRequestDto() {
        return TransactionRequestDto.builder()
                .accountId(ACCOUNT_ID)
                .amount(AMOUNT)
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .build();
    }

    public static TransactionResponseDto mockTransactionResponseDto() {
        return TransactionResponseDto.builder()
                .accountId(ACCOUNT_ID)
                .amount(AMOUNT)
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .balanceAfter(AMOUNT)
                .build();
    }

    public static List<TransactionResponseDto> mockTransactionResponseDtoList() {
        List<TransactionResponseDto> list = new ArrayList<>();
        list.add(mockTransactionResponseDto());
        return list;
    }


}
