package com.tuum.app.entity;

import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Transaction {

    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private Currency currency;

    private Direction direction;

    private String description;

    public Transaction() {
    }
}
