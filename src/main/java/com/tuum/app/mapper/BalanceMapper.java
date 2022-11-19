package com.tuum.app.mapper;

import com.tuum.app.constant.Currency;
import com.tuum.app.dto.BalanceDto;
import com.tuum.app.entity.Balance;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BalanceMapper {

    public static Balance toEntity(BalanceDto dto) {
        return Balance.builder()
                .currency(dto.getCurrency())
                .amount(new BigDecimal(dto.getAmount()))
                .build();
    }

    public static Balance toEntity(Currency currency) {
        return Balance.builder()
                .currency(currency)
                .amount(BigDecimal.ZERO)
                .build();
    }

    public static BalanceDto toDto(Balance entity) {
        String amountStr = entity.getAmount().toString();
        return BalanceDto.builder()
                .currency(entity.getCurrency())
                .amount(amountStr)
                .build();
    }

    public static Set<Balance> toEntitySet(Currency[] currencies) {
        if (currencies == null) {
            return new HashSet<>();
        }
        return toEntitySet(new HashSet<>(Set.of(currencies)));
    }

    public static Set<Balance> toEntitySet(Set<Currency> currencySet) {
        if (currencySet == null || currencySet.size() == 0) {
            return new HashSet<>();
        }
        Set<Balance> balanceSet = new HashSet<>();
        for (Currency c : currencySet) {
            balanceSet.add(toEntity(c));
        }
        return balanceSet;
    }

    public static Set<BalanceDto> toDtoSet(Set<Balance> entitySet) {
        if (entitySet == null || entitySet.size() == 0) {
            return new HashSet<>();
        }
        Set<BalanceDto> dtoSet = new HashSet<>();
        for (Balance b : entitySet) {
            dtoSet.add(toDto(b));
        }
        return dtoSet;
    }
}
