package com.bank.app.controller;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.exception.InvalidCurrencyException;
import com.bank.app.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.bank.app.util.DataMock.*;
import static com.bank.app.util.HttpUtils.toJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("createAccount() - success")
    void Given_AccountDto_When_CreateAccount_Then_ReturnAccount() throws Exception {
        // Given
        AccountRequestDto requestDto = mockAccountRequestDto();
        when(accountService.createAccount(requestDto)).thenReturn(mockAccountResponseDto());
        // When
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("createAccount() - InvalidCurrencyException on invalid currency")
    void Given_InvalidCurrency_When_CreateAccount_Then_Throw2InvalidCurrencyException() throws Exception {
        // Given
        String requestJson = mockAccountRequestDtoJson().replace("EUR", "XXX");
        // When
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isBadRequest(),
                        result -> assertTrue(result.getResolvedException() instanceof InvalidCurrencyException));
    }
/*
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
    }*/
}