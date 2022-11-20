package com.bank.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bank.app.constant.Currency;
import com.bank.app.constant.Direction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {

    private Long id;

    @JsonProperty("account_id)")
    private Long accountId;

    private String amount;

    private Currency currency;

    private Direction direction;

    private String description;

    private String balanceAfter;

}
