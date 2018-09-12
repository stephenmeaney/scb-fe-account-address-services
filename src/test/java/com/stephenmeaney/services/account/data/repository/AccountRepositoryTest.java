package com.stephenmeaney.services.account.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stephenmeaney.services.account.data.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testRepositoryReturnsListOfAccounts() {
        List<Account> accountList = accountRepository.findAll();
    }

    @Test
    public void testFindAll() {
        List<Account> accountList = accountRepository.findAll();

        assertThat(accountList.get(0).getAccountId()).isEqualTo(1);
        assertThat(accountList.get(0).getFirstName()).isEqualTo("fname1");
        assertThat(accountList.get(0).getLastName()).isEqualTo("lname1");
        assertThat(accountList.get(0).getEmail()).isEqualTo("name1@email.com");
        assertThat(accountList.get(1).getAccountId()).isEqualTo(2);
        assertThat(accountList.get(1).getFirstName()).isEqualTo("fname2");
        assertThat(accountList.get(1).getLastName()).isEqualTo("lname2");
        assertThat(accountList.get(1).getEmail()).isEqualTo("name2@email.com");
    }

    @Test
    public void testFindById() {
        Account account = accountRepository.findById(1L);
        assertThat(account.getAccountId()).isEqualTo(1);
        assertThat(account.getFirstName()).isEqualTo("fname1");
        assertThat(account.getLastName()).isEqualTo("lname1");
        assertThat(account.getEmail()).isEqualTo("name1@email.com");
    }
}