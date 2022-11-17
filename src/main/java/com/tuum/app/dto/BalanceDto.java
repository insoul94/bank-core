package com.tuum.app.dto;

import com.tuum.app.constant.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDto {

    private Currency currency;

    private BigDecimal amount;
}
