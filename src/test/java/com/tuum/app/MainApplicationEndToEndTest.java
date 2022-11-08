package com.tuum.app;

import com.tuum.app.constant.Currency;
import com.tuum.app.util.HttpUtils;
import groovy.util.logging.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.tuum.app.util.TestData.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MainApplicationEndToEndTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void Should_ReturnAccount_When_PostAccount() throws Exception {

		mockMvc.perform(
						post("/account")
								.contentType(MediaType.APPLICATION_JSON)
								.content(mockAccountRequestDtoJson())
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpectAll(
						status().isCreated(),
						content().contentType(MediaType.APPLICATION_JSON),
						jsonPath("$.account_id", greaterThan(0)),
						jsonPath("$.customer_id", is(CUSTOMER_ID), Long.class),
						jsonPath("$.balances[*].currency",
								containsInAnyOrder(Currency.getValuesNamesAsStringArray())),
						jsonPath("$.balances[*].amount", everyItem(is(0)))
				);
	}

	@Test
	void Should_ReturnAccount_When_GetAccountById() throws Exception {

		mockMvc.perform(
				get("/account/{id}", ACCOUNT_ID)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpectAll(
						status().isFound(),
						content().contentType(MediaType.APPLICATION_JSON),
						jsonPath("$.account_id", is(ACCOUNT_ID), Long.class),
						jsonPath("$.customer_id", is(CUSTOMER_ID), Long.class),
						jsonPath("$.balances[*].currency",
								containsInAnyOrder(Currency.getValuesNamesAsStringArray())),
						jsonPath("$.balances[*].amount", everyItem(is(0)))
				);
	}
}
