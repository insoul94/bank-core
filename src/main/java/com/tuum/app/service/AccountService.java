package com.tuum.app.service;

import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.TransactionRequestDto;
import com.tuum.app.exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    public AccountService() {
    }

    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) throws InvalidCurrencyException {
        return AccountResponseDto.builder().build();
    }

    public AccountResponseDto getAccount(Long id) throws AccountMissingException {
        return AccountResponseDto.builder().build();
    }

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto)
            throws
            InvalidCurrencyException,
            InvalidDirectionException,
            InvalidAmountException,
            InsufficientFundsException,
            AccountMissingException,
            DescriptionMissingException {
        return TransactionResponseDto.builder().build();
    }

    public List<TransactionResponseDto> getTransactions(Long accountId) throws AccountMissingException {
        return new ArrayList<TransactionResponseDto>();
    }
}
