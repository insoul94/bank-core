package com.tuum.app.data.model.entity;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
@Builder
public class Account {

    private Long accountId;

    private Long customerId;

    private String country;

    private HashMap<Currency, BigDecimal> balances;

}
