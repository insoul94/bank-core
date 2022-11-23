package com.bank.app.controller;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.exception.AccountNotFoundException;
import com.bank.app.exception.SystemException;
import com.bank.app.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static com.bank.app.mocks.DataMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountController accountController;


    @Test
    @DisplayName("handleAccountNotFoundException() - success")
    void Given_AccountNotFoundException_When_GetAccount_Then_ReturnNotFound() throws Exception {
        // Given
        when(accountController.readAccount(ACCOUNT_ID)).thenThrow(AccountNotFoundException.class);
        // When
        mockMvc.perform(get("/account/{id}", ACCOUNT_ID)
                        .contentType(MediaType.TEXT_HTML)
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isNotFound(),
                        result -> assertTrue(result.getResolvedException() instanceof AccountNotFoundException)
                );
    }

    @Test
    @DisplayName("handleUserException() - success")
    void Given_UserException_When_PostAccount_Then_ReturnBadRequest() throws Exception {
        // Given
        when(accountController.createAccount(any(AccountRequestDto.class), any(BindingResult.class)))
                .thenThrow(UserException.class);
        // When
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockAccountRequestDtoJson())
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isBadRequest(),
                        result -> assertTrue(result.getResolvedException() instanceof UserException)
                );
    }

    @Test
    @DisplayName("handleSystemException() - success")
    void Given_SystemException_When_PostAccount_Then_ReturnInternalServerError() throws Exception {
        // Given
        when(accountController.createAccount(any(AccountRequestDto.class), any(BindingResult.class)))
                .thenThrow(SystemException.class);
        // When
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockAccountRequestDtoJson())
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andExpectAll(
                        status().isInternalServerError(),
                        result -> assertTrue(result.getResolvedException() instanceof SystemException)
                );
    }
}