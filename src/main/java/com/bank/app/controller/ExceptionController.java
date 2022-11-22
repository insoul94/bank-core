package com.bank.app.controller;

import com.bank.app.dto.ExceptionDto;
import com.bank.app.exception.AccountNotFoundException;
import com.bank.app.exception.AppException;
import com.bank.app.exception.SystemException;
import com.bank.app.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    // TODO: Note that in order for an @ControllerAdvice subclass to be detected, ExceptionHandlerExceptionResolver must be configured.

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleAccountNotFoundException(AccountNotFoundException ex) {
        return wrapIntoResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDto> handleUserException(UserException ex) {
        return wrapIntoResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    // TODO: should handle all the rest Exceptions?
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionDto> handleSystemException(SystemException ex) {
        return wrapIntoResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionDto> wrapIntoResponseEntity(AppException ex, HttpStatus status) {
        log.error(
                """

                        ----------------------------------------------------------
                        \tStatus: \t\t\t{}
                        \tException Class: \t{}
                        \tMessage: \t\t\t{}
                        ----------------------------------------------------------
                        \t""",
                status,
                ex.getClass(),
                ex.getMessage());

        return ResponseEntity
                .status(status)
                .body(ExceptionDto.builder()
                        .message(ex.getMessage())
                        .build());
    }
}
