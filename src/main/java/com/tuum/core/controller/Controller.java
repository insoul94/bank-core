package com.tuum.core.controller;

import com.tuum.core.data.entity.Account;
import com.tuum.core.data.entity.Transaction;
import com.tuum.core.data.exception.*;
import com.tuum.core.data.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    private final AccountService accountService;

    public Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    //TODO @Valid, @RequestBody?, @ResponseBody?
    // TODO ResponseEntity.created(URI).body(accountService.getAccount(id));??
    // TODO What is the correct Http Response: Headers, statuses, body??
    // TODO URISyntaxException where to catch??

    @PostMapping(path = "account", consumes = "application/json")
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
            throws InvalidCurrencyException, URISyntaxException {
        return ResponseEntity
                .created(new URI("/account/" + account.getId()))
                .body(accountService.createAccount(account));
    }

    @GetMapping(path = "account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id)
            throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
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
        return new ResponseEntity<>(accountService.getTransactions(accountId), HttpStatus.OK);
    }
}
