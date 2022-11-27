package com.bank.app.mapper;

import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.bank.app.util.DataMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TransactionMapperTest {

    @Test
    @DisplayName("toEntity() - success")
    void Given_TransactionRequestDtoAndAccount_When_ToEntity_Then_ReturnTransaction() {
        // Given
        Account account = mockAccount();
        TransactionRequestDto requestDto = mockTransactionRequestDto();
        requestDto.setAccountId(account.getId());
        // When
        Transaction entity = TransactionMapper.toEntity(requestDto, account);
        // Then
        assertAll(
                () -> assertThat(entity.getAccount()).isEqualTo(account),
                () -> assertThat(entity.getAmount().toString()).isEqualTo(requestDto.getAmount()),
                () -> assertThat(entity.getCurrency()).isEqualTo(requestDto.getCurrency()),
                () -> assertThat(entity.getDirection()).isEqualTo(requestDto.getDirection()),
                () -> assertThat(entity.getDescription()).isEqualTo(requestDto.getDescription()));
    }

    @Test
    @DisplayName("toResponseDto() - success")
    void Given_TransactionAndBalanceAfter_When_ToResponseDto_Then_ReturnTransactionResponseDto() {
        // Given
        Transaction entity = mockTransaction();
        BigDecimal balanceAfter = new BigDecimal("1000.00");
        // When
        TransactionResponseDto responseDto = TransactionMapper.toResponseDto(entity, balanceAfter);
        // Then
        assertAll(
                () -> assertThat(responseDto.getId()).isEqualTo(entity.getId()),
                () -> assertThat(responseDto.getAccountId()).isEqualTo(entity.getAccount().getId()),
                () -> assertThat(responseDto.getAmount()).isEqualTo(entity.getAmount().toString()),
                () -> assertThat(responseDto.getCurrency()).isEqualTo(entity.getCurrency()),
                () -> assertThat(responseDto.getDirection()).isEqualTo(entity.getDirection()),
                () -> assertThat(responseDto.getDescription()).isEqualTo(entity.getDescription()),
                () -> assertThat(responseDto.getBalanceAfter()).isEqualTo(balanceAfter.toString()));
    }

    @Test
    @DisplayName("toResponseDtoList() - success")
    void Given_TransactionList_When_ToResponseDtoList_Then_ReturnTransactionResponseDtoList() {
        // Given
        Transaction entity = mockTransaction();
        List<Transaction> entityList = new ArrayList<>();
        entityList.add(entity);
        // When
        List<TransactionResponseDto> responseDtoList = TransactionMapper.toResponseDtoList(entityList);
        // Then
        assertThat(responseDtoList.size()).isEqualTo(entityList.size());
    }

    @Test
    @DisplayName("toResponseDtoList() - empty list on empty entityList")
    void Given_EmptyTransactionList_When_ToResponseDtoList_Then_ReturnEmptyList() {
        // Given, When
        List<TransactionResponseDto> responseDtoList = TransactionMapper.toResponseDtoList(new ArrayList<>());
        // Then
        assertThat(responseDtoList.size()).isEqualTo(0);
    }
}