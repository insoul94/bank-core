package com.tuum.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionRequestDto {

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty
    private BigDecimal amount;

    @JsonProperty
    private Currency currency;

    @JsonProperty
    private Direction direction;

    @JsonProperty
    private String description;
}
