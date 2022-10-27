package com.tuum.app.controller;

import com.tuum.app.data.model.entity.Account;
import com.tuum.app.data.model.entity.Currency;
import com.tuum.app.data.model.mapper.Mapper;
import com.tuum.app.data.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static com.tuum.app.testdata.TestData.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(Controller.class)
class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void Should_ReturnAccount_When_GetAccountById() throws Exception {
        Account account = Account.builder()
                .accountId(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .balances(Mapper.parseCurrenciesToBalances(Currency.values()))
                .build();
        when(accountService.getAccount(ACCOUNT_ID)).thenReturn(account);

        mockMvc.perform(
                get("/account/{id}", ACCOUNT_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.accountId", is((int) ACCOUNT_ID)),
                        jsonPath("$.customerId", is((int) CUSTOMER_ID)),
                        jsonPath("$.balances." + Currency.EUR.name(), is(0)),
                        jsonPath("$.balances." + Currency.SEK.name(), is(0)),
                        jsonPath("$.balances." + Currency.GBP.name(), is(0)),
                        jsonPath("$.balances." + Currency.USD.name(), is(0))
                );

        verify(accountService, times(1)).getAccount(ACCOUNT_ID);
    }

    @Test
    void Should_ReturnAccount_When_PostAccount() throws Exception {
        Account accountInput = Account.builder()
                .customerId(CUSTOMER_ID)
                .country(COUNTRY)
                .balances(Mapper.parseCurrenciesToBalances(Currency.values()))
                .build();
        Account accountOutput = Account.builder()
                .accountId(ACCOUNT_ID)
                .customerId(CUSTOMER_ID)
                .balances(Mapper.parseCurrenciesToBalances(Currency.values()))
                .build();
        when(accountService.createAccount(accountInput)).thenReturn(accountOutput);

        mockMvc.perform(
                post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(POST_ACCOUNT_REQUEST_JSON.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.accountId", is((int) ACCOUNT_ID)),
                        jsonPath("$.customerId", is((int) CUSTOMER_ID)),
                        jsonPath("$.balances." + Currency.EUR.name(), is(0)),
                        jsonPath("$.balances." + Currency.SEK.name(), is(0)),
                        jsonPath("$.balances." + Currency.GBP.name(), is(0)),
                        jsonPath("$.balances." + Currency.USD.name(), is(0))
                );

        verify(accountService, times(1)).createAccount(accountInput);
    }
}