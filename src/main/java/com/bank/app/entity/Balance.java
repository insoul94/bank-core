package com.bank.app.entity;

import com.bank.app.constant.Currency;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "balance", indexes = {
        @Index(name = "fk_account_id_idx", columnList = "fk_account_id")
})
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Currency currency;

    // TODO: precision, scale
    @Column(nullable = false)
    private BigDecimal amount;

    public Balance() {
    }

    public static class BalanceBuilder {
        public BalanceBuilder amount(BigDecimal amount) {
            this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
            return this;
        }
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", fk_account_id=" + account.getId() +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
