package com.bank.app.dto;

import com.bank.app.validator.ValidCurrencies;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@Builder
public class AccountRequestDto {

    @NotNull
    @Positive
    @JsonProperty("customer_id")
    private Long customerId;

    private String country;

    @ValidCurrencies
    private Set<String> currencies;
}
