package com.bank.app.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountValidatorTest {

    @Test
    @DisplayName("isValid() - true on correct input")
    void Given_CorrectInput_When_IsValid_Then_True() {
        AmountValidator validator = new AmountValidator();
        String[] input = {
                "1234567890",
                ".9",
                "1.",
                "0.9876",
                "1234.98",
                "1234.98765",
                "1.0000",
                "0001.9000",
        };

        for (String s : input) {
            assertTrue(validator.isValid(s, null));
        }
    }

    @Test
    @DisplayName("isValid() - false on incorrect input")
    void Given_IncorrectInput_When_IsValid_Then_False() {
        AmountValidator validator = new AmountValidator();
        String[] input = {
                "0",
                "00000",
                "1.98$",
                "+1.98",
                "-1.98",
                "3e+5",
                "a.bc",
                "1.2.3",
                "1 2",
                "1,234,567.98"
        };

        for (String s : input) {
            assertFalse(validator.isValid(s, null));
        }
    }
}