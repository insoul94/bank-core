package com.bank.app.controller;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.exception.AccountNotFoundException;
import com.bank.app.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static com.bank.app.util.DataMock.ACCOUNT_ID;
import static com.bank.app.util.DataMock.mockAccountRequestDtoJson;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ExceptionResolverTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountController accountController;


    @Test
    @DisplayName("handleAccountNotFoundException() - success")
    void Given_AccountNotFoundException_When_GetAccount_Then_ReturnNotFound() throws Exception {
        // Given
        when(accountController.readAccount(ACCOUNT_ID)).thenThrow(new AccountNotFoundException(0L));
        // When
        mockMvc.perform(get("/account/{id}", ACCOUNT_ID)
                        .contentType(MediaType.TEXT_HTML)
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isNotFound(),
                        result -> assertTrue(result.getResolvedException() instanceof AccountNotFoundException),
                        jsonPath("$.message", notNullValue())
                );
    }

    @Test
    @DisplayName("handleUserException() - success")
    void Given_UserException_When_PostAccount_Then_ReturnBadRequest() throws Exception {
        // Given
        when(accountController.createAccount(any(AccountRequestDto.class), any(BindingResult.class)))
                .thenThrow(new UserException());
        // When
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockAccountRequestDtoJson())
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isBadRequest(),
                        result -> assertTrue(result.getResolvedException() instanceof UserException),
                        jsonPath("$.message", notNullValue())
                );
    }

    @Test
    @DisplayName("handleAllExceptions() - success")
    void Given_AnyException_When_PostAccount_Then_ReturnInternalServerError() throws Exception {
        // Given
        when(accountController.createAccount(any(AccountRequestDto.class), any(BindingResult.class)))
                .thenThrow(new IllegalArgumentException());
        // When
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockAccountRequestDtoJson())
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isInternalServerError(),
                        result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException),
                        jsonPath("$.message", notNullValue())
                );
    }
}