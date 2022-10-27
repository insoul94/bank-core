package com.tuum.app.data.model.mapper;

import com.tuum.app.data.model.dto.AccountGetDto;
import com.tuum.app.data.model.dto.AccountPostDto;
import com.tuum.app.data.model.entity.Account;
import com.tuum.app.data.model.entity.Currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

public class Mapper {

    public static AccountGetDto accountToAccountGetDto(Account account) {
        if (account == null) {
            return null;
        }
        return AccountGetDto.builder()
                .accountId(account.getAccountId())
                .customerId(account.getCustomerId())
                .balances(account.getBalances())
                .build();
    }

    public static Account accountPostDtoToAccount(AccountPostDto accountPostDto) {
        if (accountPostDto == null) {
            return null;
        }
        return Account.builder()
                .customerId(accountPostDto.getCustomerId())
                .country(accountPostDto.getCountry())
                .balances(parseCurrenciesToBalances(accountPostDto.getCurrencies()))
                .build();
    }

    public static HashMap<Currency, BigDecimal> parseCurrenciesToBalances(Set<Currency> currencies) {
        return parseCurrenciesToBalances(currencies.toArray(new Currency[0]));
    }

    public static HashMap<Currency, BigDecimal> parseCurrenciesToBalances(Currency[] currencies) {
        HashMap<Currency, BigDecimal> balances = new HashMap<>();
        if (currencies == null) {
            return new HashMap<>();
        }
        for (Currency c : currencies) {
            balances.put(c, BigDecimal.ZERO);
        }
        return balances;
    }
}
