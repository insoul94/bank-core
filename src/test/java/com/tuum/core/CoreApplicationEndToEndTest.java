package com.tuum.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
public class CoreApplicationEndToEndTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void Should_ReturnAccount_WhenPostAccount() {

	}

	@Test
	void Should_ReturnAccount_When_GetAccountById() throws Exception {
		int accountId = 46;

		mockMvc.perform(get("/account/{id}", accountId))
				.andDo(print())
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void createTransaction() {
	}

	@Test
	void getTransactions() {
	}
}
