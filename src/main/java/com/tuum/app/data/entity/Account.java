package com.tuum.app.data.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

@Data
public class Account {

    private Long id;

    private Long customerId;

    private HashMap<Currency, BigDecimal> balances = new HashMap<>();

    public Account() {
    }

    public Account(Long id, Long customerId, Currency[] currencies) {
        this.id = id;
        this.customerId = customerId;
        this.balances = parseCurrenciesToBalances(currencies);
    }

    private static HashMap<Currency, BigDecimal> parseCurrenciesToBalances(Currency[] currencies) {
        HashMap<Currency, BigDecimal> balances = new HashMap<>();
        if (currencies == null) {
            return new HashMap<>();
        }
        for (Currency c : currencies) {
            balances.put(c, BigDecimal.ZERO);
        }
        return balances;
    }
}
