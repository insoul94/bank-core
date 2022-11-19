package com.tuum.app.dto;

import com.tuum.app.constant.Currency;
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
