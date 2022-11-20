package com.bank.app.controller;

import com.bank.app.dto.AccountRequestDto;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.dto.TransactionRequestDto;
import com.bank.app.dto.TransactionResponseDto;
import com.bank.app.exception.*;
import com.bank.app.mocks.DataMock;
import com.bank.app.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
        AccountRequestDto request = DataMock.mockAccountRequestDto();
        AccountResponseDto response = DataMock.mockAccountResponseDto();
        when(accountService.createAccount(request)).thenReturn(response);

        AccountResponseDto actual = controller.createAccount(request).getBody();

        assertThat(actual, is(response));
        verify(accountService).createAccount(request);
    }

    @Test
    @DisplayName("readAccount - success")
    void Given_AccountId_When_ReadAccount_Then_ReturnAccount() throws Exception {
        AccountResponseDto response = DataMock.mockAccountResponseDto();
        when(accountService.getAccount(DataMock.ACCOUNT_ID)).thenReturn(response);

        AccountResponseDto actual = controller.readAccount(DataMock.ACCOUNT_ID).getBody();

        assertThat(actual, is(response));
        verify(accountService).getAccount(DataMock.ACCOUNT_ID);
    }

    @Test
    @DisplayName("createTransaction - success")
    void Given_TransactionRequestDto_When_CreateTransaction_Then_ReturnTransactionResponseDto()
            // TODO: group exceptions
            throws AccountMissingException, InvalidAmountException, InsufficientFundsException, InvalidDirectionException, DescriptionMissingException, InvalidCurrencyException {

                TransactionRequestDto request = DataMock.mockTransactionRequestDto();
                TransactionResponseDto response = DataMock.mockTransactionResponseDto();
                when(accountService.createTransaction(request)).thenReturn(response);

                TransactionResponseDto actual = controller.createTransaction(request).getBody();

                assertThat(actual, is(response));
                verify(accountService).createTransaction(request);
            }

    @Test
    @DisplayName("readTransaction - success")
    void Given_AccountId_When_ReadTransaction_Then_ReturnTransactionList() throws AccountMissingException {
        List<TransactionResponseDto> response = DataMock.mockTransactionResponseDtoList();
        when(accountService.getTransactions(DataMock.ACCOUNT_ID)).thenReturn(response);

        List<TransactionResponseDto> actual = controller.readTransactions(DataMock.ACCOUNT_ID).getBody();

        assertThat(actual, is(response));
        verify(accountService).getTransactions(DataMock.ACCOUNT_ID);
    }
}