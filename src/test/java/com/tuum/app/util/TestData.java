package com.tuum.app.util;

import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.TransactionRequestDto;
import com.tuum.app.entity.Account;

import java.math.BigDecimal;
import java.util.Random;

import static com.tuum.app.util.HttpUtils.*;

public class TestData {

    public final static long ACCOUNT_ID = 1;
    // Test only positive numbers
    public final static Long CUSTOMER_ID = new Random().nextLong(Long.SIZE-1);
    public final static int TRANSACTION_ID = 333;
    public final static BigDecimal AMOUNT = BigDecimal.valueOf(1000.00);
    public final static String COUNTRY = "Estonia";
    public final static String DESCRIPTION = "payout";
    public final static Currency CURRENCY = Currency.EUR;

    private TestData() {
    }

    public static Account mockAccount() {
        return Account.builder()
                .id(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .balances(HttpUtils.parseIntoBalances(Currency.values()))
                .build();
    }

    public static String mockAccountRequestDtoJson() {
        return toJson(AccountRequestDto.builder()
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .currencies(Currency.valuesAsSet())
                .build());
    }

    public static String mockTransactionRequestDtoJson() {
        return toJson(TransactionRequestDto.builder()
                .accountId(ACCOUNT_ID)
                .amount(AMOUNT)
                .currency(CURRENCY)
                .direction(Direction.IN)
                .description(DESCRIPTION)
                .build());
    }
}
