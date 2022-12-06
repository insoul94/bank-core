package com.bank.app.mapper;

import com.bank.app.constant.Currency;
import com.bank.app.dto.BalanceDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Balance;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BalanceMapper {

    public static Balance toEntity(@NotNull String currencyStr, @NotNull Account account) {
        return Balance.builder()
                .account(account)
                .currency(Currency.valueOf(currencyStr))
                .amount(BigDecimal.ZERO)
                .build();
    }

    public static Set<Balance> toEntitySet(Set<String> currencyStrSet, @NotNull Account account) {
        if (currencyStrSet == null || currencyStrSet.size() == 0) {
            return new HashSet<>();
        }
        Set<Balance> balanceSet = new HashSet<>();
        for (String currencyStr : currencyStrSet) {
            Balance balance = toEntity(currencyStr, account);
            balance.setAccount(account);
            balanceSet.add(balance);
        }
        return balanceSet;
    }

    public static BalanceDto toDto(@NotNull Balance entity) {
        // TODO: CHECK HERE!!!!
        String amountStr = entity.getAmount().toString();
        return BalanceDto.builder()
                .currency(entity.getCurrency())
                .amount(amountStr)
                .build();
    }


    public static Set<BalanceDto> toDtoSet(@NotNull Set<Balance> entitySet) {
        if (entitySet.size() == 0) {
            return new HashSet<>();
        }
        Set<BalanceDto> dtoSet = new HashSet<>();
        for (Balance balance : entitySet) {
            dtoSet.add(toDto(balance));
        }
        return dtoSet;
    }
}
