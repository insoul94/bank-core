package com.tuum.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionResponseDto {

    private Long id;

    @JsonProperty("account_id)")
    private Long accountId;

    private BigDecimal amount;

    private Currency currency;

    private Direction direction;

    private String description;

    private BigDecimal balanceAfter;
}
