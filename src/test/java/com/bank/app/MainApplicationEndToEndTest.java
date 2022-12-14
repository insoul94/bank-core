package com.bank.app;

import com.bank.app.constant.Currency;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.util.HttpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.atomic.AtomicLong;

import static com.bank.app.mocks.DataMock.*;
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

		mockMvc.perform(post("/account")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mockAccountRequestDtoJson())
						.accept(MediaType.APPLICATION_JSON))

				.andExpectAll(
						status().isCreated(),
						content().contentType(MediaType.APPLICATION_JSON),
						jsonPath("$.id", greaterThan(0)),
						jsonPath("$.customer_id", is(CUSTOMER_ID), Long.class),
						jsonPath("$.balances[*].currency",
								containsInAnyOrder(Currency.valuesAsStringArray())),
						jsonPath("$.balances[*].amount", everyItem(is("0.00")))
				);
	}

	@Test
	void Should_ReturnAccount_When_GetAccountById() throws Exception {
		AtomicLong accountId = new AtomicLong();

		mockMvc.perform(post("/account")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mockAccountRequestDtoJson())
						.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isCreated())
				.andDo((r) -> {
					AccountResponseDto responseDto = HttpUtils.fromJson(
							r.getResponse().getContentAsString(), AccountResponseDto.class);
					accountId.set(responseDto.getId());
				});


		mockMvc.perform(get("/account/{id}", accountId)
						.accept(MediaType.APPLICATION_JSON))

				.andExpectAll(
						status().isFound(),
						content().contentType(MediaType.APPLICATION_JSON),
						jsonPath("$.id", is(accountId.longValue()), Long.class),
						jsonPath("$.customer_id", is(CUSTOMER_ID), Long.class),
						jsonPath("$.balances[*].currency", containsInAnyOrder(Currency.valuesAsStringArray())),
						jsonPath("$.balances[*].amount", everyItem(is("0.00")))
				);
	}
}
