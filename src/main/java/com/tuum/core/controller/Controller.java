package com.tuum.core.controller;

import com.tuum.core.data.entity.Account;
import com.tuum.core.data.entity.Transaction;
import com.tuum.core.data.exception.*;
import com.tuum.core.data.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
public class Controller {

    private final AccountService accountService;

    public Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    //TODO @Valid, @RequestBody?
    @PostMapping(path = "account", consumes = "application/json")
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
            throws InvalidCurrencyException {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping(path = "account/{id}", consumes = "application/json")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id)
            throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.FOUND);
    }

    @PostMapping(path = "transaction", consumes = "application/json")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction)
            throws
            AccountMissingException,
            InvalidAmountException,
            InsufficientFundsException,
            InvalidDirectionException,
            DescriptionMissingException,
            InvalidCurrencyException {
        return new ResponseEntity<>(accountService.createTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping(path = "transaction/{id}", consumes = "application/json")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("id") Long accountId)
            throws AccountMissingException {
        return new ResponseEntity<>(accountService.getTransactions(accountId), HttpStatus.FOUND);
    }
}
