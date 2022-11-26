package com.bank.app.repository;

import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.List;

import static com.bank.app.util.DataMock.mockAccount;
import static com.bank.app.util.DataMock.mockTransaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@EntityScan("com.bank.app.entity")
@EnableJpaRepositories("com.bank.app.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("save() - success")
    void Given_Transaction_When_Save_Success() {
        // Given
        Account account = accountRepository.save(mockAccount());
        Transaction transaction = mockTransaction();
        transaction.setAccount(account);
        // When
        Transaction savedTransaction = transactionRepository.save(transaction);
        // Then
        assertAll(
                () -> assertThat(savedTransaction.getId()).isGreaterThan(0),
                () -> assertThat(savedTransaction.getAccount()).isEqualTo(account),
                () -> assertThat(savedTransaction.getAmount()).isEqualTo(transaction.getAmount()),
                () -> assertThat(savedTransaction.getCurrency()).isEqualTo(transaction.getCurrency()),
                () -> assertThat(savedTransaction.getDirection()).isEqualTo(transaction.getDirection()),
                () -> assertThat(savedTransaction.getDescription()).isEqualTo(transaction.getDescription()));
    }

    @Test
    @DisplayName("findByAccount() - success")
    void Given_Account_When_FindByAccount_Success() {
        // Given
        Account account = accountRepository.save(mockAccount());
        Transaction transaction = mockTransaction();
        transaction.setAccount(account);
        transactionRepository.save(transaction);
        // When
        List<Transaction> transactionList = transactionRepository.findByAccount(account);
        // Then
        assertAll(
                () -> assertThat(transactionList.size()).isGreaterThan(0),
                () -> assertThat(transactionList.get(0).getAccount()).isEqualTo(account));
    }
}