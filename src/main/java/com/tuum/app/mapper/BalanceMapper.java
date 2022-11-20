package com.tuum.app.mapper;

import com.tuum.app.constant.Currency;
import com.tuum.app.dto.BalanceDto;
import com.tuum.app.entity.Account;
import com.tuum.app.entity.Balance;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BalanceMapper {

    public static Balance toEntity(Currency currency, Account account) {
        if (currency == null || account == null) {
            return new Balance();
        }
        return Balance.builder()
                .account(account)
                .currency(currency)
                .amount(BigDecimal.ZERO)
                .build();
    }

    public static Set<Balance> toEntitySet(Set<Currency> currencySet, Account account) {
        if (currencySet == null || currencySet.size() == 0 || account == null) {
            return new HashSet<>();
        }
        Set<Balance> balanceSet = new HashSet<>();
        for (Currency currency : currencySet) {
            Balance balance = toEntity(currency, account);
            balance.setAccount(account);
            balanceSet.add(balance);
        }
        return balanceSet;
    }

    public static BalanceDto toDto(Balance entity) {
        if (entity == null) {
            return new BalanceDto();
        }
        // TODO: CHECK HERE!!!!
        String amountStr = entity.getAmount().toString();
        return BalanceDto.builder()
                .currency(entity.getCurrency())
                .amount(amountStr)
                .build();
    }


    public static Set<BalanceDto> toDtoSet(Set<Balance> entitySet) {
        if (entitySet == null || entitySet.size() == 0) {
            return new HashSet<>();
        }
        Set<BalanceDto> dtoSet = new HashSet<>();
        for (Balance balance : entitySet) {
            dtoSet.add(toDto(balance));
        }
        return dtoSet;
    }
}
