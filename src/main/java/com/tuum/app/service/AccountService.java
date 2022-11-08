package com.tuum.app.service;

import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.TransactionRequestDto;
import com.tuum.app.entity.Account;
import com.tuum.app.exception.*;
import com.tuum.app.repository.AccountRepository;
import com.tuum.app.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // TODO: where to check notnull?
    public AccountResponseDto createAccount(@NotNull AccountRequestDto requestDto) throws InvalidCurrencyException {
        log.info(requestDto.toString());
        Account account = HttpUtils.parseIntoAccount(requestDto);
        log.info(account.toString());
        Account savedAccount = accountRepository.save(account);
        log.info(savedAccount.toString());
        AccountResponseDto responseDto = HttpUtils.parseIntoAccountResponseDto(savedAccount);
        log.info(responseDto.toString());
        return responseDto;
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
