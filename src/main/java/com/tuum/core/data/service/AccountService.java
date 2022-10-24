package com.tuum.core.data.service;

import com.tuum.core.data.entity.Account;
import com.tuum.core.data.entity.Transaction;
import com.tuum.core.data.exception.*;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    public AccountService() {
    }

    public Account createAccount(Account account) throws InvalidCurrencyException {
        return new Account();
    }

    public Account getAccount(Long id) throws AccountNotFoundException {
        return new Account(46L);
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
