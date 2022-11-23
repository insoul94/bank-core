package com.bank.app.service;

import com.bank.app.constant.Currency;
import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.BalanceDto;
import com.bank.app.entity.Account;
import com.bank.app.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.bank.app.mocks.DataMock.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("createAccount() - success")
    void Given_AccountRequestDto_When_createAccount_Then_ReturnAccountResponseDto() {
        // Given
        AccountRequestDto requestDto = mockAccountRequestDto();
        Account account = mockAccount();
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        // When
        AccountResponseDto responseDto = accountService.createAccount(requestDto);
        // Then
        assertAll(
                () -> assertThat(responseDto.getId(), greaterThan(0L)),
                () -> assertThat(responseDto.getCustomerId(), is(requestDto.getCustomerId())),
                () -> assertThat(responseDto.getBalanceDtoSet().stream().map(BalanceDto::getCurrency).toList(),
                        containsInAnyOrder(Currency.values())),
                () -> assertThat(responseDto.getBalanceDtoSet().stream().map(BalanceDto::getAmount).toList(),
                        everyItem(is("0.00")))
        );
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("readAccount() - success")
    void Given_ExistingAccountId_When_readAccount_Then_ReturnAccountResponseDto() {
        // Given
        Account account = mockAccount();
        when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(account));
        // When
        AccountResponseDto responseDto = accountService.readAccount(ACCOUNT_ID);
        // Then
        assertAll(
                () -> assertThat(responseDto.getId(), greaterThan(0L)),
                () -> assertThat(responseDto.getCustomerId(), is(account.getCustomerId())),
                () -> assertThat(responseDto.getBalanceDtoSet().stream().map(BalanceDto::getCurrency).toList(),
                        containsInAnyOrder(Currency.values())),
                () -> assertThat(responseDto.getBalanceDtoSet().stream().map(BalanceDto::getAmount).toList(),
                        everyItem(is("0.00")))
        );
        verify(accountRepository).findById(ACCOUNT_ID);
    }
}