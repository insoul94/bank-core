package com.bank.app.util;

import com.bank.app.dto.*;
import com.bank.app.entity.Balance;
import com.bank.app.entity.Transaction;
import com.bank.app.mapper.BalanceMapper;
import com.bank.app.constant.Currency;
import com.bank.app.constant.Direction;
import com.bank.app.entity.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.bank.app.util.HttpUtils.*;

public class DataMock {

    public final static long ACCOUNT_ID = getRandomLong();
    public final static long CUSTOMER_ID = getRandomLong();
    public final static long TRANSACTION_ID = getRandomLong();
    public final static BigDecimal AMOUNT = getRandomAmount();
    public final static String COUNTRY = getRandomCountry();
    public final static String DESCRIPTION = "Test Description";
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
                .amount(AMOUNT)
                .build();
    }
    public static Set<Balance> mockBalanceSet() {
        return BalanceMapper.toEntitySet(Currency.valuesAsStringSet(), mockAccount());
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
        account.setBalances(BalanceMapper.toEntitySet(Currency.valuesAsStringSet(), account));
        return account;
    }

    public static AccountRequestDto mockAccountRequestDto() {
        return AccountRequestDto.builder()
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .currencies(Currency.valuesAsStringSet())
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
                .amount(AMOUNT.toString())
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .build();
    }

    public static Transaction mockTransaction() {
        return Transaction.builder()
                .id(TRANSACTION_ID)
                .account(mockAccount())
                .amount(AMOUNT)
                .currency(Currency.EUR)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .build();
    }

    public static TransactionResponseDto mockTransactionResponseDto() {
        return TransactionResponseDto.builder()
                .accountId(ACCOUNT_ID)
                .amount(AMOUNT.toString())
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .balanceAfter(AMOUNT.toString())
                .build();
    }

    public static List<TransactionResponseDto> mockTransactionResponseDtoList() {
        List<TransactionResponseDto> list = new ArrayList<>();
        list.add(mockTransactionResponseDto());
        return list;
    }


}
