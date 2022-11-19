package com.tuum.app.mapper;

import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.entity.Account;

public class AccountMapper {

    public static AccountResponseDto toResponseDto(Account entity) {
        // TODO Where to handle null? Or return empty object?
        if (entity == null) {
            return null;
        }
        return AccountResponseDto.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .balanceDtos(BalanceMapper.toDtoSet(entity.getBalances()))
                .build();
    }

    /**
     * @param requestDto
     * @return Account with null 'id' field.
     */
    public static Account toEntity(AccountRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        return Account.builder()
                .customerId(requestDto.getCustomerId())
                .country(requestDto.getCountry())
                .balances(BalanceMapper.toEntitySet(requestDto.getCurrencies()))
                .build();
    }
}
