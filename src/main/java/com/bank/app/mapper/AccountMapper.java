package com.bank.app.mapper;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Balance;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class AccountMapper {

    /**
     * @param requestDto
     * @return Account with null 'id' field.
     */
    public static Account toEntity(@NotNull AccountRequestDto requestDto) {
        Account account = Account.builder()
                .customerId(requestDto.getCustomerId())
                .country(requestDto.getCountry())
                .build();
        Set<Balance> balanceSet = BalanceMapper.toEntitySet(requestDto.getCurrencies(), account);
        account.setBalances(balanceSet);
        return account;
    }

    public static AccountResponseDto toResponseDto(@NotNull Account entity) {
        return AccountResponseDto.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .balanceDtoSet(BalanceMapper.toDtoSet(entity.getBalances()))
                .build();
    }
}
