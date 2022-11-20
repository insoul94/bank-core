package com.bank.app.dto;

import com.bank.app.constant.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

    private Currency currency;

    private String amount;

}
