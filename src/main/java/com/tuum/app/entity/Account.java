package com.tuum.app.entity;


import com.tuum.app.constant.Currency;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.HashMap;

@Data
@Builder
public class Account {

    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    // @Transient ?
    private String country;

    // TODO separate column for each currency?
    private HashMap<Currency, BigDecimal> balances;

}
