package com.bank.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bank.app.constant.Currency;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Builder
public class AccountRequestDto {

    @NotNull
    @JsonProperty("customer_id")
    private Long customerId;

    private String country;

    private Set<Currency> currencies;
}
