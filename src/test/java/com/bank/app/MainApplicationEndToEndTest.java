package com.bank.app;

import com.bank.app.constant.Currency;
import com.bank.app.dto.AccountResponseDto;
import com.bank.app.util.HttpUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicLong;

import static com.bank.app.util.DataMock.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MainApplicationEndToEndTest {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@DisplayName("POST /account - created")
	void Given_CorrectInput_When_PostAccount_Then_Success() throws Exception {

		mockMvc.perform(post("/account")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mockAccountRequestDtoJson())
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())

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
	@DisplayName("POST /account - bad request on invalid currency")
	void Given_InvalidCurrency_When_PostAccount_Then_BadRequest() throws Exception {

		mockMvc.perform(post("/account")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mockAccountRequestWithInvalidCurrencyJson())
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())

				.andExpectAll(
						status().isBadRequest()
				);
	}


	@Test
	@DisplayName("GET /account/{id} - found")
	void Given_ExistingAccountId_When_GetAccount_Then_Success() throws Exception {
		// Given
		AtomicLong accountId = new AtomicLong();

		mockMvc.perform(post("/account")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mockAccountRequestDtoJson())
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())

				.andExpect(status().isCreated())
				.andDo((r) -> {
					AccountResponseDto responseDto = HttpUtils.fromJson(
							r.getResponse().getContentAsString(), AccountResponseDto.class);
					accountId.set(responseDto.getId());
				});
		// When
		mockMvc.perform(get("/account/{id}", accountId)
						.contentType(MediaType.TEXT_HTML)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				// Then
				.andExpectAll(
						status().isFound(),
						content().contentType(MediaType.APPLICATION_JSON),
						jsonPath("$.id", is(accountId.longValue()), Long.class),
						jsonPath("$.customer_id", is(CUSTOMER_ID), Long.class),
						jsonPath("$.balances[*].currency", containsInAnyOrder(Currency.valuesAsStringArray())),
						jsonPath("$.balances[*].amount", everyItem(is("0.00")))
				);
	}

	@Test
	@DisplayName("GET /account/{id} - not found on not existing")
	void Given_NotExistingAccountId_When_GetAccount_Then_BadRequest() throws Exception {

		mockMvc.perform(get("/account/{id}", getRandomLong())
						.contentType(MediaType.TEXT_HTML)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())

				.andExpectAll(
						status().isNotFound()
				);
	}
}
