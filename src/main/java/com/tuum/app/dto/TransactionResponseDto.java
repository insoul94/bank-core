package com.tuum.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

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


    public static class TransactionResponseDtoBuilder {
        public TransactionResponseDtoBuilder amount(BigDecimal amount) {
            this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
            return this;
        }
        public TransactionResponseDtoBuilder balanceAfter(BigDecimal balanceAfter) {
            this.balanceAfter = balanceAfter.setScale(2, RoundingMode.HALF_EVEN);
            return this;
        }
    }
}
