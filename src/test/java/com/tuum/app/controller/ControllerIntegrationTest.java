package com.tuum.app.controller;

import com.tuum.app.data.entity.Account;
import com.tuum.app.data.entity.Currency;
import com.tuum.app.data.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static com.tuum.app.TestData.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        when(accountService.getAccount(ACCOUNT_ID))
                .thenReturn(new Account(ACCOUNT_ID, CUSTOMER_ID, Currency.values()));

        mockMvc.perform(get("/account/{id}", ACCOUNT_ID))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id", is((int)ACCOUNT_ID)),
                        jsonPath("$.customerId", is((int)CUSTOMER_ID)),
                        jsonPath("$.balances." + Currency.EUR.name(), is(0)),
                        jsonPath("$.balances." + Currency.SEK.name(), is(0)),
                        jsonPath("$.balances." + Currency.GBP.name(), is(0)),
                        jsonPath("$.balances." + Currency.USD.name(), is(0))
                );

        verify(accountService).getAccount(ACCOUNT_ID);
    }

}