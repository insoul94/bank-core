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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.bank.app.mocks.DataMock.*;

class BalanceMapperTest {

    @Test
    @DisplayName("toEntity() - success")
    void Given_CurrencyAndAccount_When_ToEntity_Then_ReturnBalance() {
        Account account = mockAccount();

        Balance entity = BalanceMapper.toEntity(Currency.EUR, account);

        assertAll(
                () -> assertThat(entity.getId(), nullValue()),
                () -> assertThat(entity.getAccount(), is(account)),
                () -> assertThat(entity.getCurrency(), is(Currency.EUR)),
                () -> assertThat(entity.getAmount(), is(new BigDecimal("0.00"))));
    }

    @Test
    @DisplayName("toEntity() - success on null Currency")
    void Given_NullAndAccount_When_ToEntity_Then_ReturnEmptyBalance() {
        Account account = mockAccount();

        Balance entity = BalanceMapper.toEntity(null, account);

        assertAll(
                () -> assertThat(entity.getId(), nullValue()),
                () -> assertThat(entity.getAccount(), nullValue()),
                () -> assertThat(entity.getCurrency(), nullValue()),
                () -> assertThat(entity.getAmount(), nullValue()));
    }

    @Test
    @DisplayName("toEntity() - success on null Account")
    void Given_CurrencyAndNull_When_ToEntity_Then_ReturnEmptyBalance() {
        Balance entity = BalanceMapper.toEntity(Currency.EUR, null);

        assertAll(
                () -> assertThat(entity.getId(), nullValue()),
                () -> assertThat(entity.getAccount(), nullValue()),
                () -> assertThat(entity.getCurrency(), nullValue()),
                () -> assertThat(entity.getAmount(), nullValue()));
    }


    @Test
    @DisplayName("toEntitySet() - success")
    void Given_CurrencySetAndAccount_When_ToEntitySet_Then_ReturnBalanceSet() {
        Account account = mockAccount();

        Set<Balance> entitySet = BalanceMapper.toEntitySet(Currency.valuesAsSet(), account);

        assertAll(
                () -> assertThat(entitySet.stream().map(Balance::getId).toList(),
                        everyItem(nullValue())),
                () -> assertThat(entitySet.stream().map(Balance::getAccount).toList(),
                        everyItem(is(account))),
                () -> assertThat(entitySet.stream().map(Balance::getCurrency).toList(),
                        containsInAnyOrder(Currency.values())),
                () -> assertThat(entitySet.stream().map(Balance::getAmount).toList(),
                        everyItem(is(new BigDecimal("0.00")))));
    }

    @Test
    @DisplayName("toEntitySet() - success on empty set")
    void Given_EmptyCurrencySet_When_ToEntitySet_Then_ReturnEmptySet() {
        Account account = mockAccount();

        Set<Balance> entitySet = BalanceMapper.toEntitySet(new HashSet<>(), account);

        assertThat(entitySet.size(), is(0));
    }

    @Test
    @DisplayName("toEntitySet() - success on null Account")
    void Given_CurrencySetAndNull_When_ToEntitySet_Then_ReturnEmptySet() {
        Set<Balance> entitySet = BalanceMapper.toEntitySet(Currency.valuesAsSet(), null);

        assertThat(entitySet.size(), is(0));
    }

    @Test
    @DisplayName("toDto() - success")
    void Given_Balance_When_ToDto_Then_ReturnBalanceDto() {
        Balance entity = mockBalance();

        BalanceDto dto = BalanceMapper.toDto(entity);

        assertAll(
                () -> assertThat(dto.getAmount(), is(AMOUNT)),
                () -> assertThat(dto.getCurrency(), is(CURRENCY)));
    }

    @Test
    @DisplayName("toDto() - success on null")
    void Given_Null_When_ToDto_Then_ReturnEmptyBalanceDto() {
        BalanceDto dto = BalanceMapper.toDto(null);

        assertAll(
                () -> assertThat(dto.getAmount(), nullValue()),
                () -> assertThat(dto.getCurrency(), nullValue()));
    }

    @Test
    @DisplayName("toDtoSet() - success")
    void Given_BalanceSet_When_ToDtoSet_Then_ReturnBalanceDtoSet() {
        Set<Balance> balanceSet = mockBalanceSet();

        Set<BalanceDto> dtoSet = BalanceMapper.toDtoSet(balanceSet);

        assertAll(
                () -> assertThat(dtoSet.stream().map(BalanceDto::getCurrency).toList(),
                        containsInAnyOrder(Currency.values())),
                () -> assertThat(dtoSet.stream().map(BalanceDto::getAmount).toList(),
                        everyItem(is("0.00"))));
    }

    @Test
    @DisplayName("toDtoSet() - success on empty set")
    void Given_EmptySet_When_ToDtoSet_Then_ReturnEmptySet() {
        Set<BalanceDto> dtoSet = BalanceMapper.toDtoSet(null);

        assertThat(dtoSet.size(), is(0));
    }
}