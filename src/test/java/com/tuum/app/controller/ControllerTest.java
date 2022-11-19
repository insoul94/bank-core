package com.tuum.app.controller;

import com.tuum.app.dto.AccountRequestDto;
import com.tuum.app.dto.AccountResponseDto;
import com.tuum.app.dto.TransactionRequestDto;
import com.tuum.app.dto.TransactionResponseDto;
import com.tuum.app.exception.*;
import com.tuum.app.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.tuum.app.mocks.DataMock.*;
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
    @DisplayName("createAccount - success")
    void Given_AccountDto_When_CreateAccount_Then_ReturnAccount() throws InvalidCurrencyException {
        AccountRequestDto request = mockAccountRequestDto();
        AccountResponseDto response = mockAccountResponseDto();
        when(accountService.createAccount(request)).thenReturn(response);

        AccountResponseDto actual = controller.createAccount(request).getBody();

        assertThat(actual, is(response));
        verify(accountService).createAccount(request);
    }

    @Test
    @DisplayName("readAccount - success")
    void Given_AccountId_When_ReadAccount_Then_ReturnAccount() throws Exception {
        AccountResponseDto response = mockAccountResponseDto();
        when(accountService.getAccount(ACCOUNT_ID)).thenReturn(response);

        AccountResponseDto actual = controller.readAccount(ACCOUNT_ID).getBody();

        assertThat(actual, is(response));
        verify(accountService).getAccount(ACCOUNT_ID);
    }

    @Test
    @DisplayName("createTransaction - success")
    void Given_TransactionRequestDto_When_CreateTransaction_Then_ReturnTransactionResponseDto()
            // TODO: group exceptions
            throws AccountMissingException, InvalidAmountException, InsufficientFundsException, InvalidDirectionException, DescriptionMissingException, InvalidCurrencyException {

                TransactionRequestDto request = mockTransactionRequestDto();
                TransactionResponseDto response = mockTransactionResponseDto();
                when(accountService.createTransaction(request)).thenReturn(response);

                TransactionResponseDto actual = controller.createTransaction(request).getBody();

                assertThat(actual, is(response));
                verify(accountService).createTransaction(request);
            }

    @Test
    @DisplayName("readTransaction - success")
    void Given_AccountId_When_ReadTransaction_Then_ReturnTransactionList() throws AccountMissingException {
        List<TransactionResponseDto> response = mockTransactionResponseDtoList();
        when(accountService.getTransactions(ACCOUNT_ID)).thenReturn(response);

        List<TransactionResponseDto> actual = controller.readTransactions(ACCOUNT_ID).getBody();

        assertThat(actual, is(response));
        verify(accountService).getTransactions(ACCOUNT_ID);
    }
}