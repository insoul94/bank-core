package com.bank.app.controller;

import com.bank.app.dto.ExceptionDto;
import com.bank.app.exception.AccountNotFoundException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleAllExceptions(Exception ex) {
        return wrapIntoResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionDto> wrapIntoResponseEntity(Exception ex, HttpStatus status) {
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
                        .message(status == HttpStatus.INTERNAL_SERVER_ERROR ?
                                "Internal Server Error" : ex.getMessage())
                        .build());
    }
}
