package com.bank.app.service;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.entity.Account;
import com.bank.app.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static com.bank.app.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.bank.app.util.DataMock.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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
                () -> assertThat(responseDto.getId()).isGreaterThan(0L),
                () -> assertThat(responseDto.getCustomerId()).isEqualTo(requestDto.getCustomerId()),
                () -> assertThat(getCurrencyArray(responseDto)).containsExactlyInAnyOrder(getCurrencyArray(account)),
                () -> assertThat(responseDto.getBalanceDtoSet()).allMatch(dto -> dto.getAmount().equals("0.00"))
        );
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
                () -> assertThat(responseDto.getId()).isEqualTo(ACCOUNT_ID),
                () -> assertThat(responseDto.getCustomerId()).isEqualTo(account.getCustomerId()),
                () -> assertThat(getCurrencyArray(responseDto))
                        .containsExactlyInAnyOrder(getCurrencyArray(account)),
                () -> assertThat(responseDto.getBalanceDtoSet()).allMatch(dto -> dto.getAmount().equals("0.00"))
        );
    }
}