package com.tuum.app.entity;

import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

//@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Transaction {

    //@GenerationType.SEQUENCE?
    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private Currency currency;

    private Direction direction;

    private String description;

    public Transaction() {
    }
}
