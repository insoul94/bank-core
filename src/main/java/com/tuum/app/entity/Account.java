package com.tuum.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Balance> balances;

    public Account() {
    }
}
