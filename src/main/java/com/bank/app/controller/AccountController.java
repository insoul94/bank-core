package com.bank.app.controller;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.exception.*;
import com.bank.app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping(
            path = "account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<AccountResponseDto> createAccount(
            @RequestBody @Valid AccountRequestDto accountRequestDto, BindingResult bindingResult)
            throws InvalidCurrencyException {

        if (bindingResult.hasFieldErrors("currency")) {
            throw new InvalidCurrencyException();
        } else if (bindingResult.hasErrors()) {
            throw new UserException();
        }

        return new ResponseEntity<>(
                accountService.createAccount(accountRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/account/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<AccountResponseDto> readAccount(@PathVariable("id") Long id)
            throws AccountNotFoundException {

        return new ResponseEntity<>(
                accountService.readAccount(id), HttpStatus.FOUND);
    }



    @PostMapping(
            path = "transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<TransactionResponseDto> createTransaction(
            @RequestBody TransactionRequestDto transactionRequestDto)
            throws
            AccountMissingException,
            InvalidAmountException,
            InsufficientFundsException,
            InvalidDirectionException,
            DescriptionMissingException,
            InvalidCurrencyException {

        return new ResponseEntity<>(
                accountService.createTransaction(transactionRequestDto),
                HttpStatus.CREATED);
    }

    @GetMapping(
            path = "transaction/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<TransactionResponseDto>> readTransactions(
            @PathVariable("id") Long accountId)
            throws AccountMissingException {

        return new ResponseEntity<>(
                accountService.readTransactions(accountId),
                HttpStatus.FOUND);
    }
}
