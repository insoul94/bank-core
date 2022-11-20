package com.tuum.app.mapper;

import com.tuum.app.constant.Currency;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.BalanceDto;
import com.tuum.app.entity.Account;
import com.tuum.app.entity.Balance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static com.tuum.app.mocks.DataMock.*;

class AccountMapperTest {

    @Test
    @DisplayName("toEntity() - success")
    void Given_AccountResponseDto_When_ToEntity_Then_ReturnAccount() {
        AccountRequestDto requestDto = mockAccountRequestDto();

        Account entity = AccountMapper.toEntity(requestDto);

        List<Account> entityBalancesAccountList = entity.getBalances().stream().map(Balance::getAccount).toList();
        List<Currency> entityCurrencyList = entity.getBalances().stream().map(Balance::getCurrency).toList();
        List<BigDecimal> entityAmountList = entity.getBalances().stream().map(Balance::getAmount).toList();
        assertAll(
                () -> assertThat(entity.getCustomerId(), is(requestDto.getCustomerId())),
                () -> assertThat(entity.getCountry(), is(requestDto.getCountry())),
                () -> assertThat(entityBalancesAccountList, everyItem(is(entity))),
                () -> assertThat(entityCurrencyList, containsInAnyOrder(requestDto.getCurrencies().toArray())),
                () -> assertThat(entityAmountList, everyItem(is(new BigDecimal("0.00")))));
    }

    @Test
    @DisplayName("toEntity() - success on null")
    void Given_Null_When_ToEntity_Then_ReturnEmptyAccount() {
        Account entity = AccountMapper.toEntity(null);

        assertAll(
                () -> assertThat(entity.getId(), nullValue()),
                () -> assertThat(entity.getCustomerId(), nullValue()),
                () -> assertThat(entity.getCountry(), nullValue()),
                () -> assertThat(entity.getBalances(), nullValue()));
    }

    @Test
    @DisplayName("toResponseDto() - success")
    void Given_Account_When_ToResponseDto_Then_ReturnAccountResponseDto() {
        Account account = mockAccount();

        AccountResponseDto responseDto = AccountMapper.toResponseDto(account);

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
                () -> assertThat(responseDto.getId(), is(account.getId())),
                () -> assertThat(responseDto.getCustomerId(), is(account.getCustomerId())),
                () -> assertThat(responseDtoBalanceSet, containsInAnyOrder(entityBalanceSet.toArray())));
    }

    @Test
    @DisplayName("toResponseDto() - success on null")
    void Given_Null_When_ToResponseDto_Then_ReturnEmptyAccountResponseDto() {
        AccountResponseDto responseDto = AccountMapper.toResponseDto(null);

        assertAll(
                () -> assertThat(responseDto.getId(), nullValue()),
                () -> assertThat(responseDto.getCustomerId(), nullValue()),
                () -> assertThat(responseDto.getBalanceDtoSet(), nullValue()));
    }
}