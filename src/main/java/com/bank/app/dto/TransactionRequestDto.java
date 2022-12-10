package com.bank.app.dto;

import com.bank.app.validator.ValidAmount;
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
public class TransactionRequestDto {

    @NotNull
    @Positive
    @JsonProperty("account_id")
    private Long accountId;

    @ValidAmount
    private String amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Direction direction;

    @NotBlank
    private String description;

}
