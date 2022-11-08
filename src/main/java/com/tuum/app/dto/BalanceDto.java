package com.tuum.app.dto;

import com.tuum.app.constant.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceDto {

    private Currency currency;

    private BigDecimal amount;
}
