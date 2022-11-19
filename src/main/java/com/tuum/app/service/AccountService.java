package com.tuum.app.service;

import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.TransactionRequestDto;
import com.tuum.app.entity.Account;
import com.tuum.app.exception.*;
import com.tuum.app.mapper.AccountMapper;
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

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    // TODO: where to check notnull?
    // TODO: should the interface be added to lock service behaviour?
    public AccountResponseDto createAccount(@NotNull AccountRequestDto requestDto) throws InvalidCurrencyException {
        Account account = AccountMapper.toEntity(requestDto);

        // TODO: should fields assignment be implicit?
        account.getBalances()
                .forEach(balance ->
                        balance.setAccount(account));

        Account savedAccount = accountRepository.save(account);
        AccountResponseDto responseDto = AccountMapper.toResponseDto(savedAccount);
        return responseDto;
    }

    public AccountResponseDto getAccount(Long id) throws AccountMissingException {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountMissingException::new);

        AccountResponseDto responseDto = AccountMapper.toResponseDto(account);
        return responseDto;
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
