package com.tuum.app.entity;

import com.tuum.app.constant.Currency;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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
