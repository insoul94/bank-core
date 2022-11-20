package com.bank.app.service;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.exception.*;
import com.bank.app.mapper.AccountMapper;
import com.bank.app.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    // TODO: where to check notnull?
    // TODO: should the interface be added to lock service behaviour?
    public AccountResponseDto createAccount(@NotNull AccountRequestDto requestDto) throws InvalidCurrencyException {
        Account account = AccountMapper.toEntity(requestDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toResponseDto(savedAccount);
    }

    public AccountResponseDto getAccount(@NotNull Long id) throws AccountMissingException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountMissingException::new);

        return AccountMapper.toResponseDto(account);
    }

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto)
            throws
            InvalidCurrencyException,
            InvalidDirectionException,
            InvalidAmountException,
            InsufficientFundsException,
            AccountMissingException,
            DescriptionMissingException {

        // optimized search
        Account proxy = accountRepository.getReferenceById(transactionRequestDto.getAccountId());

        return TransactionResponseDto.builder().build();
    }

    public List<TransactionResponseDto> getTransactions(Long accountId) throws AccountMissingException {
        //
        return new ArrayList<TransactionResponseDto>();
    }
}
