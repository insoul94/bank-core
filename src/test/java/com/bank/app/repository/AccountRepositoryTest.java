package com.bank.app.repository;

import com.bank.app.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.Optional;

import static com.bank.app.util.DataMock.mockAccount;
import static com.bank.app.util.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@EntityScan("com.bank.app.entity")
@EnableJpaRepositories("com.bank.app.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("save() - success")
    void Given_Account_When_Save_Success() {
        // Given
        Account account = mockAccount();
        // When
        Account savedAccount = accountRepository.save(account);
        // Then
        assertAll(
                () -> assertThat(savedAccount.getId()).isGreaterThan(0),
                () -> assertThat(savedAccount.getCustomerId()).isEqualTo(account.getCustomerId()),
                () -> assertThat(savedAccount.getCountry()).isEqualTo(account.getCountry()),
                () -> assertThat(getCurrencyArray(savedAccount)).containsOnly(getCurrencyArray(account)),
                () -> assertThat(savedAccount.getBalances()).allSatisfy(balance -> {
                            assertThat(balance.getId()).isGreaterThan(0);
                            assertThat(balance.getAccount()).isEqualTo(savedAccount);
                            assertThat(balance.getAmount()).isEqualTo(new BigDecimal("0.00"));
                        }
                ));
    }

    @Test
    @DisplayName("findById() - success")
    void Given_ExistingAccountId_When_FindById_Success() {
        // Given
        Account savedAccount = accountRepository.save(mockAccount());
        // When
        Optional<Account> optional = accountRepository.findById(savedAccount.getId());
        // Then
        assertThat(optional.isPresent()).isTrue();
    }
}