package com.tuum.app.data.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.app.data.model.entity.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
@Builder
public class AccountGetDto {
    @JsonProperty("accountId")
    private Long accountId;

    @JsonProperty("customerId")
    private Long customerId;

    @JsonProperty("balances")
    private HashMap<Currency, BigDecimal> balances;
}
