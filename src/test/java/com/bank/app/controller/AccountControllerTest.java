package com.bank.app.controller;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.exception.*;
import com.bank.app.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static com.bank.app.util.DataMock.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private BindingResult bindingResult;


    @Test
    @DisplayName("createAccount() - success")
    void Given_AccountDto_When_CreateAccount_Then_ReturnAccount() {
        // Given
        AccountRequestDto request = mockAccountRequestDto();
        AccountResponseDto response = mockAccountResponseDto();
        when(accountService.createAccount(request)).thenReturn(response);
        // When
        AccountResponseDto actual = accountController.createAccount(request, bindingResult).getBody();
        // Then
        assertThat(actual).isEqualTo(response);
    }

    @Test
    @DisplayName("createAccount() - InvalidCurrencyException on invalid currency")
    void Given_InvalidCurrency_When_CreateAccount_Then_ThrowInvalidCurrencyException() {
        // Given
        AccountRequestDto request = mockAccountRequestDto();
        when(bindingResult.hasFieldErrors("currency")).thenReturn(true);
        // When, Then
        assertThrows(InvalidCurrencyException.class, () -> accountController.createAccount(request, bindingResult));
    }

    @Test
    @DisplayName("readAccount() - success")
    void Given_AccountId_When_ReadAccount_Then_ReturnAccountResponseDto() {
        // Given
        AccountResponseDto response = mockAccountResponseDto();
        when(accountService.readAccount(ACCOUNT_ID)).thenReturn(response);
        // When
        AccountResponseDto actual = accountController.readAccount(ACCOUNT_ID).getBody();
        // Then
        assertThat(actual).isEqualTo(response);
    }

    @Test
    @DisplayName("createTransaction() - success")
    void Given_TransactionRequestDto_When_CreateTransaction_Then_ReturnTransactionResponseDto() throws UserException {
        // Given
        TransactionRequestDto request = mockTransactionRequestDto();
        TransactionResponseDto response = mockTransactionResponseDto();
        when(accountService.createTransaction(request)).thenReturn(response);
        // When
        TransactionResponseDto actual = accountController.createTransaction(request).getBody();
        // Then
        assertThat(actual).isEqualTo(response);
    }

    @Test
    @DisplayName("readTransaction() - success")
    void Given_AccountId_When_ReadTransaction_Then_ReturnTransactionList() throws AccountMissingException {
        // Given
        List<TransactionResponseDto> response = mockTransactionResponseDtoList();
        when(accountService.readTransactions(ACCOUNT_ID)).thenReturn(response);
        // When
        List<TransactionResponseDto> actual = accountController.readTransactions(ACCOUNT_ID).getBody();
        // Then
        assertThat(actual).isEqualTo(response);
    }
}