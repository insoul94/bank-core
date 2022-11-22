package com.bank.app.controller;

import com.bank.app.dto.ExceptionDto;
import com.bank.app.exception.AccountNotFoundException;
import com.bank.app.exception.SystemException;
import com.bank.app.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionControllerTest {

    private final ExceptionController exceptionController = new ExceptionController();

    @Test
    @DisplayName("handleAccountNotFoundException - success")
    void Given_AccountNotFoundException_When_HandleAccountNotFoundException_Then_ReturnResponseEntityNotFound() {
        ResponseEntity<ExceptionDto> response =
                exceptionController.handleAccountNotFoundException(new AccountNotFoundException("Account Not Found"));

        assertAll(
                () -> assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND)),
                () -> assertThat(response.getBody().getMessage(), is("Account Not Found"))
        );
    }

    @Test
    @DisplayName("handleUserException - success")
    void Given_UserException_When_HandleUserException_Then_ReturnResponseEntityBadRequest() {
        ResponseEntity<ExceptionDto> response =
                exceptionController.handleUserException(new UserException());

        assertAll(
                () -> assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST)),
                () -> assertThat(response.getBody().getMessage(), is("Invalid input data"))
        );
    }

    @Test
    @DisplayName("handleSystemException - success")
    void Given_SystemException_When_HandleSystemException_Then_ReturnResponseEntityInternalServerError() {
        ResponseEntity<ExceptionDto> response =
                exceptionController.handleSystemException(new SystemException());

        assertAll(
                () -> assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR)),
                () -> assertThat(response.getBody().getMessage(), is("Internal server error"))
        );
    }
}