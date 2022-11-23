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
import static com.bank.app.mocks.DataMock.*;

class BalanceMapperTest {

    @Test
    @DisplayName("toEntity() - success")
    void Given_CurrencyAndAccount_When_ToEntity_Then_ReturnBalance() {
        Account account = mockAccount();

        Balance entity = BalanceMapper.toEntity(Currency.EUR, account);

        assertAll(
                () -> assertThat(entity.getId()).isNull(),
                () -> assertThat(entity.getAccount()).isEqualTo(account),
                () -> assertThat(entity.getCurrency()).isEqualTo(Currency.EUR),
                () -> assertThat(entity.getAmount()).isEqualTo(new BigDecimal("0.00")));
    }

    @Test
    @DisplayName("toEntity() - return empty Balance on null Currency")
    void Given_NullAndAccount_When_ToEntity_Then_ReturnEmptyBalance() {
        Account account = mockAccount();

        Balance entity = BalanceMapper.toEntity(null, account);

        assertAll(
                () -> assertThat(entity.getId()).isNull(),
                () -> assertThat(entity.getAccount()).isNull(),
                () -> assertThat(entity.getCurrency()).isNull(),
                () -> assertThat(entity.getAmount()).isNull());
    }

    @Test
    @DisplayName("toEntity() - return empty Balance on null Account")
    void Given_CurrencyAndNull_When_ToEntity_Then_ReturnEmptyBalance() {
        Balance entity = BalanceMapper.toEntity(Currency.EUR, null);

        assertAll(
                () -> assertThat(entity.getId()).isNull(),
                () -> assertThat(entity.getAccount()).isNull(),
                () -> assertThat(entity.getCurrency()).isNull(),
                () -> assertThat(entity.getAmount()).isNull());
    }


    @Test
    @DisplayName("toEntitySet() - success")
    void Given_CurrencySetAndAccount_When_ToEntitySet_Then_ReturnBalanceSet() {
        Account account = mockAccount();

        Set<Balance> entitySet = BalanceMapper.toEntitySet(Currency.valuesAsSet(), account);

        assertAll(
                () -> assertThat(entitySet.stream().map(Balance::getCurrency).toList()).containsOnly(Currency.values()),
                () -> assertThat(entitySet).allSatisfy(entity -> {
                    assertThat(entity.getId()).isNull();
                    assertThat(entity.getAccount()).isEqualTo(account);
                    assertThat(entity.getAmount()).isEqualTo(new BigDecimal("0.00"));
                })
        );
        assertThat(entitySet).allSatisfy(entity -> {
            assertThat(entity.getId()).isNull();
            assertThat(entity.getAccount()).isEqualTo(account);
            assertThat(entity.getAmount()).isEqualTo(new BigDecimal("0.00"));
        });
    }

    @Test
    @DisplayName("toEntitySet() - return empty HashSet on empty set")
    void Given_EmptyCurrencySet_When_ToEntitySet_Then_ReturnEmptySet() {
        Account account = mockAccount();

        Set<Balance> entitySet = BalanceMapper.toEntitySet(new HashSet<>(), account);

        assertThat(entitySet.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("toEntitySet() - return empty Balance on null Account")
    void Given_CurrencySetAndNull_When_ToEntitySet_Then_ReturnEmptySet() {
        Set<Balance> entitySet = BalanceMapper.toEntitySet(Currency.valuesAsSet(), null);

        assertThat(entitySet.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("toDto() - success")
    void Given_Balance_When_ToDto_Then_ReturnBalanceDto() {
        Balance entity = mockBalance();

        BalanceDto dto = BalanceMapper.toDto(entity);

        assertAll(
                () -> assertThat(dto.getAmount()).isEqualTo(AMOUNT),
                () -> assertThat(dto.getCurrency()).isEqualTo(CURRENCY));
    }

    @Test
    @DisplayName("toDto() - return empty BalanceDto on null")
    void Given_Null_When_ToDto_Then_ReturnEmptyBalanceDto() {
        BalanceDto dto = BalanceMapper.toDto(null);

        assertAll(
                () -> assertThat(dto.getAmount()).isNull(),
                () -> assertThat(dto.getCurrency()).isNull());
    }

    @Test
    @DisplayName("toDtoSet() - success")
    void Given_BalanceSet_When_ToDtoSet_Then_ReturnBalanceDtoSet() {
        Set<Balance> balanceSet = mockBalanceSet();

        Set<BalanceDto> dtoSet = BalanceMapper.toDtoSet(balanceSet);

        assertAll(
                () -> assertThat(dtoSet.stream().map(BalanceDto::getCurrency).toList()).containsOnly(Currency.values()),
                () -> assertThat(dtoSet).allMatch(dto -> dto.getAmount().equals("0.00")));
    }

    @Test
    @DisplayName("toDtoSet() - return empty HashSet on empty set")
    void Given_EmptySet_When_ToDtoSet_Then_ReturnEmptySet() {
        Set<BalanceDto> dtoSet = BalanceMapper.toDtoSet(new HashSet<>());

        assertThat(dtoSet.size()).isEqualTo(0);
    }
}