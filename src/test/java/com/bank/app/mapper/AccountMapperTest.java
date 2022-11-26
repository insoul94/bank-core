package com.bank.app.mapper;

import com.bank.app.constant.Currency;
import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.BalanceDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Balance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static com.bank.app.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static com.bank.app.util.DataMock.*;

class AccountMapperTest {

    @Test
    @DisplayName("toEntity() - success")
    void Given_AccountResponseDto_When_ToEntity_Then_ReturnAccount() {
        // Given
        AccountRequestDto requestDto = mockAccountRequestDto();
        // When
        Account entity = AccountMapper.toEntity(requestDto);
        // Then
        assertAll(
                () -> assertThat(entity.getCustomerId()).isEqualTo(requestDto.getCustomerId()),
                () -> assertThat(entity.getCountry()).isEqualTo(requestDto.getCountry()),
                () -> assertThat(getCurrencyArray(entity))
                        .containsExactlyInAnyOrderElementsOf(requestDto.getCurrencies()),
                () -> assertThat(entity.getBalances()).allSatisfy(balance -> {
                    assertThat(balance.getAccount()).isEqualTo(entity);
                    assertThat(balance.getAmount()).isEqualTo(new BigDecimal("0.00"));
                }));
    }

    @Test
    @DisplayName("toEntity() - return empty Account on null")
    void Given_Null_When_ToEntity_Then_ReturnEmptyAccount() {
        // Given, When
        Account entity = AccountMapper.toEntity(null);
        // Then
        assertAll(
                () -> assertThat(entity.getId()).isNull(),
                () -> assertThat(entity.getCustomerId()).isNull(),
                () -> assertThat(entity.getCountry()).isNull(),
                () -> assertThat(entity.getBalances()).isNull());
    }

    @Test
    @DisplayName("toResponseDto() - success")
    void Given_Account_When_ToResponseDto_Then_ReturnAccountResponseDto() {
        // Given
        Account account = mockAccount();
        // WHen
        AccountResponseDto responseDto = AccountMapper.toResponseDto(account);
        // Then
        Set<Map.Entry<Currency, String>> responseDtoBalanceSet =
                responseDto.getBalanceDtoSet()
                        .stream()
                        .collect(Collectors.toMap(BalanceDto::getCurrency, BalanceDto::getAmount))
                        .entrySet();
        Set<Map.Entry<Currency, String>> entityBalanceSet =
                account.getBalances()
                        .stream()
                        .collect(Collectors.toMap(Balance::getCurrency, bal -> bal.getAmount().toString()))
                        .entrySet();
        assertAll(
                () -> assertThat(responseDto.getId()).isEqualTo(account.getId()),
                () -> assertThat(responseDto.getCustomerId()).isEqualTo(account.getCustomerId()),
                () -> assertThat(responseDtoBalanceSet).containsExactlyInAnyOrderElementsOf(entityBalanceSet));
    }

    @Test
    @DisplayName("toResponseDto() - return empty AccountResponseDto on null")
    void Given_Null_When_ToResponseDto_Then_ReturnEmptyAccountResponseDto() {
        // Given, When
        AccountResponseDto responseDto = AccountMapper.toResponseDto(null);
        // Then
        assertAll(
                () -> assertThat(responseDto.getId()).isNull(),
                () -> assertThat(responseDto.getCustomerId()).isNull(),
                () -> assertThat(responseDto.getBalanceDtoSet()).isNull());
    }
}