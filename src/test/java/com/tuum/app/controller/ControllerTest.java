package com.tuum.app.controller;

import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.TransactionRequestDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.exception.*;
import com.tuum.app.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import static com.tuum.app.testutil.MockData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private Controller controller;

    @Test
    void Given_AccountDto_When_PostAccount_Then_ReturnAccount() throws InvalidCurrencyException {
        AccountRequestDto request = mockAccountRequestDto();
        AccountResponseDto response = mockAccountResponseDto();
        when(accountService.createAccount(request)).thenReturn(response);

        AccountResponseDto actual = controller.createAccount(request).getBody();

        assertThat(actual, is(response));
        verify(accountService).createAccount(request);
    }

    @Test
    void Given_AccountId_When_GetAccount_Then_ReturnAccount() throws Exception {
        AccountResponseDto response = mockAccountResponseDto();
        when(accountService.getAccount(ACCOUNT_ID)).thenReturn(response);

        AccountResponseDto actual = controller.getAccount(ACCOUNT_ID).getBody();

        assertThat(actual, is(response));
        verify(accountService).getAccount(ACCOUNT_ID);
    }

    @Test
    void Given_TransactionRequestDto_When_PostTransaction_Then_ReturnTransactionResponseDto()
            // TODO:
            throws AccountMissingException, InvalidAmountException, InsufficientFundsException, InvalidDirectionException, DescriptionMissingException, InvalidCurrencyException {

                TransactionRequestDto request = mockTransactionRequestDto();
                TransactionResponseDto response = mockTransactionResponseDto();
                when(accountService.createTransaction(request)).thenReturn(response);

                TransactionResponseDto actual = controller.createTransaction(request).getBody();

                assertThat(actual, is(response));
                verify(accountService).createTransaction(request);
            }

    @Test
    void Given_AccountId_When_GetTransaction_Then_ReturnTransactionList() throws AccountMissingException {
        List<TransactionResponseDto> response = mockTransactionResponseDtoList();
        when(accountService.getTransactions(ACCOUNT_ID)).thenReturn(response);

        List<TransactionResponseDto> actual = controller.getTransactions(ACCOUNT_ID).getBody();

        assertThat(actual, is(response));
        verify(accountService).getTransactions(ACCOUNT_ID);
    }
}