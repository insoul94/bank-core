package com.bank.app.entity;

import com.bank.app.constant.Currency;
import com.bank.app.constant.Direction;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "transaction", indexes = {
        @Index(name = "transaction_fk_account_id_idx", columnList = "fk_account_id")
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_account_id", nullable = false, updatable = false)
    private Account account;

    @Column(nullable = false, updatable = false, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Direction direction;

    @Column(nullable = false, updatable = false)
    private String description;


    public static class TransactionBuilder {
        public TransactionBuilder amount(BigDecimal amount) {
            this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
            return this;
        }
    }
}
