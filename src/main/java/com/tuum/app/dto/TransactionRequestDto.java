package com.tuum.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Builder
public class TransactionRequestDto {

    @Positive
    @JsonProperty("account_id")
    private Long accountId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Direction direction;

    @NotBlank
    private String description;

    public static class TransactionRequestDtoBuilder {
        public TransactionRequestDtoBuilder amount(BigDecimal amount) {
            this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
            return this;
        }
    }
}
