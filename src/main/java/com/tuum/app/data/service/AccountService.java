package com.tuum.app.data.service;

import com.tuum.app.data.model.entity.Account;
import com.tuum.app.data.model.entity.Transaction;
import com.tuum.app.data.exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    public AccountService() {
    }

    public Account createAccount(Account account) throws InvalidCurrencyException {
        return account;
    }

    public Account getAccount(Long id) throws AccountMissingException {
        return Account.builder().build();
    }

    public Transaction createTransaction(Transaction transaction)
            throws
            InvalidCurrencyException,
            InvalidDirectionException,
            InvalidAmountException,
            InsufficientFundsException,
            AccountMissingException,
            DescriptionMissingException {
        return new Transaction();
    }

    public List<Transaction> getTransactions(Long accountId) throws AccountMissingException {
        return new ArrayList<Transaction>();
    }
}
