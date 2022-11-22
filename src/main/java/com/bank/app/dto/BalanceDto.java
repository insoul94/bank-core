package com.bank.app.dto;

import com.bank.app.constant.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

    @NotNull
    private Currency currency;

    @Positive
    private String amount;

}
