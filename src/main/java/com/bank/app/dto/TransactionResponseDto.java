package com.bank.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bank.app.constant.Currency;
import com.bank.app.constant.Direction;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class TransactionResponseDto {

    @Positive
    private Long id;

    @JsonProperty("account_id)")
    @Positive
    private Long accountId;

    @NotBlank
    private String amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Direction direction;

    private String description;

    @NotNull
    private String balanceAfter;

}
