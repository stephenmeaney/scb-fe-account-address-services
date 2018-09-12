package com.stephenmeaney.services.account.data.repository;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.address.data.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testPersistData() {
        Account account = new Account();
        account.setFirstName("firstname");
        account.setLastName("lastname");
        account.setEmail("name@email.com");
        account.setAddressList(new ArrayList<Address>());

        entityManager.persistAndFlush(account);

        Account foundAccount = accountRepository.findById(1L);

        assertThat(foundAccount.getAccountId()).isEqualTo(1L);
        assertThat(foundAccount.getFirstName()).isEqualTo("firstname");
        assertThat(foundAccount.getLastName()).isEqualTo("lastname");
        assertThat(foundAccount.getEmail()).isEqualTo("name@email.com");
    }

}
