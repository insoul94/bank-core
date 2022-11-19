package com.tuum.app.entity;

import com.tuum.app.constant.Currency;
import com.tuum.app.constant.Direction;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

//@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
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


    public static class TransactionBuilder {
        public TransactionBuilder amount(BigDecimal amount) {
            this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
            return this;
        }
    }
}
