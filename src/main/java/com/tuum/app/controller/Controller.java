package com.tuum.app.controller;

import com.tuum.app.data.model.dto.AccountGetDto;
import com.tuum.app.data.model.dto.AccountPostDto;
import com.tuum.app.data.model.entity.Account;
import com.tuum.app.data.exception.*;
import com.tuum.app.data.model.mapper.Mapper;
import com.tuum.app.data.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class Controller {

    private final AccountService accountService;

    public Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    // TODO URISyntaxException where to catch??

    @GetMapping(
            path = "account/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<AccountGetDto> getAccount(@PathVariable("id") Long id)
            throws AccountMissingException {

        Account account = accountService.getAccount(id);
        AccountGetDto accountGetDto = Mapper.accountToAccountGetDto(account);

        return new ResponseEntity<>(accountGetDto, HttpStatus.OK);
    }


    @PostMapping(
            path = "account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<AccountGetDto> createAccount(@RequestBody @Valid AccountPostDto accountPostDto)
            throws InvalidCurrencyException {

        Account account = Mapper.accountPostDtoToAccount(accountPostDto);
        account = accountService.createAccount(account);
        AccountGetDto accountGetDto = Mapper.accountToAccountGetDto(account);

        return new ResponseEntity<>(accountGetDto, HttpStatus.CREATED);
    }


   /* @GetMapping(
            path = "transaction/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("id") Long accountId)
            throws AccountMissingException {

        return new ResponseEntity<>(accountService.getTransactions(accountId), HttpStatus.OK);
    }


    @PostMapping(
            path = "transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction)
            throws
            AccountMissingException,
            InvalidAmountException,
            InsufficientFundsException,
            InvalidDirectionException,
            DescriptionMissingException,
            InvalidCurrencyException {

        return new ResponseEntity<>(accountService.createTransaction(transaction), HttpStatus.CREATED);
    }*/
}
