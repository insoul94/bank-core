package com.bank.app.util;

import com.bank.app.constant.Currency;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.BalanceDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Balance;


public class TestUtil {

    public static Currency[] getCurrencyArray(Account account) {
        return account.getBalances().stream().map(Balance::getCurrency).toArray(Currency[]::new);
    }

    public static Currency[] getCurrencyArray(AccountResponseDto accountResponseDto) {
        return accountResponseDto.getBalanceDtoSet().stream().map(BalanceDto::getCurrency).toArray(Currency[]::new);
    }
}
