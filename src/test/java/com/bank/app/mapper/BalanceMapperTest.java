package com.bank.app.mapper;

import com.bank.app.dto.BalanceDto;
import com.bank.app.entity.Balance;
import com.bank.app.constant.Currency;
import com.bank.app.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static com.bank.app.util.DataMock.*;

class BalanceMapperTest {

    @Test
    @DisplayName("toEntity() - success")
    void Given_CurrencyAndAccount_When_ToEntity_Then_ReturnBalance() {
        // Given
        Account account = mockAccount();
        // When
        Balance entity = BalanceMapper.toEntity(Currency.EUR.name(), account);
        // Then
        assertAll(
                () -> assertThat(entity.getId()).isNull(),
                () -> assertThat(entity.getAccount()).isEqualTo(account),
                () -> assertThat(entity.getCurrency()).isEqualTo(Currency.EUR),
                () -> assertThat(entity.getAmount()).isEqualTo(new BigDecimal("0.00")));
    }

    @Test
    @DisplayName("toEntitySet() - success")
    void Given_CurrencySetAndAccount_When_ToEntitySet_Then_ReturnBalanceSet() {
        // Given
        Account account = mockAccount();
        // When
        Set<Balance> entitySet = BalanceMapper.toEntitySet(Currency.valuesAsStringSet(), account);
        // Then
        assertAll(
                () -> assertThat(entitySet.stream().map(Balance::getCurrency).toList())
                        .containsExactlyInAnyOrder(Currency.values()),
                () -> assertThat(entitySet).allSatisfy(entity -> {
                    assertThat(entity.getId()).isNull();
                    assertThat(entity.getAccount()).isEqualTo(account);
                    assertThat(entity.getAmount()).isEqualTo(new BigDecimal("0.00"));
                })
        );
    }

    @Test
    @DisplayName("toEntitySet() - return empty HashSet on empty set")
    void Given_EmptyCurrencySet_When_ToEntitySet_Then_ReturnEmptySet() {
        // Given
        Account account = mockAccount();
        // When
        Set<Balance> entitySet = BalanceMapper.toEntitySet(new HashSet<>(), account);
        // Then
        assertThat(entitySet.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("toDto() - success")
    void Given_Balance_When_ToDto_Then_ReturnBalanceDto() {
        // Given
        Balance entity = mockBalance();
        // When
        BalanceDto dto = BalanceMapper.toDto(entity);
        // Then
        assertAll(
                () -> assertThat(dto.getAmount()).isEqualTo(AMOUNT.toString()),
                () -> assertThat(dto.getCurrency()).isEqualTo(CURRENCY));
    }

    @Test
    @DisplayName("toDtoSet() - success")
    void Given_BalanceSet_When_ToDtoSet_Then_ReturnBalanceDtoSet() {
        // Given
        Set<Balance> balanceSet = mockBalanceSet();
        // When
        Set<BalanceDto> dtoSet = BalanceMapper.toDtoSet(balanceSet);
        // Then
        assertAll(
                () -> assertThat(dtoSet.stream().map(BalanceDto::getCurrency).toList())
                        .containsExactlyInAnyOrder(Currency.values()),
                () -> assertThat(dtoSet).allMatch(dto -> dto.getAmount().equals("0.00")));
    }

    @Test
    @DisplayName("toDtoSet() - return empty HashSet on empty set")
    void Given_EmptySet_When_ToDtoSet_Then_ReturnEmptySet() {
        // Given, When
        Set<BalanceDto> dtoSet = BalanceMapper.toDtoSet(new HashSet<>());
        // Then
        assertThat(dtoSet.size()).isEqualTo(0);
    }
}