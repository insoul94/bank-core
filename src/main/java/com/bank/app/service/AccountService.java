package com.bank.app.service;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;
import com.bank.app.exception.AccountMissingException;
import com.bank.app.exception.AccountNotFoundException;
import com.bank.app.exception.UserException;
import com.bank.app.mapper.AccountMapper;
import com.bank.app.mapper.TransactionMapper;
import com.bank.app.dao.AccountRepository;
import com.bank.app.dao.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // TODO: should the interface be added to lock service behaviour?
    // TODO: validate BigDecimal input fields
    // TODO: Custom validator -> InvalidCurrencyException and all other ones
    // TODO: SHould countries be validated?
    public AccountResponseDto createAccount(@NotNull AccountRequestDto requestDto) {
        Account account = AccountMapper.toEntity(requestDto);
        Account savedAccount = accountRepository.saveAndFlush(account);
        return AccountMapper.toResponseDto(savedAccount);
    }

    public AccountResponseDto readAccount(@NotNull Long id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return AccountMapper.toResponseDto(account);
    }

    public TransactionResponseDto createTransaction(@NotNull TransactionRequestDto requestDto) throws UserException {
        Account account = accountRepository.findById(requestDto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(requestDto.getAccountId()));

        Transaction transaction = TransactionMapper.toEntity(requestDto, account);
        process(account, transaction);
        Transaction savedTransaction = transactionRepository.saveAndFlush(transaction);

        return TransactionMapper.toResponseDto(transaction);
    }

    public List<TransactionResponseDto> readTransactions(@NotNull Long accountId) throws AccountMissingException {
//        Account proxy;
//        try {
//            proxy = accountRepository.getReferenceById(accountId);
//        } catch (EntityNotFoundException e) {
//            throw new AccountNotFoundException(accountId);
//        }
//        List<Transaction> transactionList = transactionRepository.findByAccount(proxy);
        return new ArrayList<TransactionResponseDto>();
//        return transactionList.size() == 0 ? new ArrayList<>() : TransactionMapper.toResponseDtoList();
    }

    private void process(Account account, Transaction transaction) {

    }
}
