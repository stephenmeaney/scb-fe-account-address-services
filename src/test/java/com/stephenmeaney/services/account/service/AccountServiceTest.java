package com.stephenmeaney.services.account.service;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.data.repository.AccountRepository;
import com.stephenmeaney.services.address.data.entity.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountService.class})
public class AccountServiceTest {

    @MockBean
    private AccountRepository mockAccountRepository;

    @Autowired
    private AccountService accountService;

    public Account createMockAccount() {
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setFirstName("first");
        mockAccount.setLastName("last");
        mockAccount.setEmail("name@email.com");
        mockAccount.setAddressList(new ArrayList<Address>());

        return mockAccount;
    }

    @Test
    public void testGetById() {
        Account mockAccount = createMockAccount();

        when(mockAccountRepository.findById(anyLong())).thenReturn(mockAccount);

        Account returnedAccount = accountService.getById(1L);

        assertThat(returnedAccount.getAccountId()).isEqualTo(1L);
        assertThat(returnedAccount.getFirstName()).isEqualTo("first");
        assertThat(returnedAccount.getLastName()).isEqualTo("last");
        assertThat(returnedAccount.getEmail()).isEqualTo("name@email.com");
        assertThat(returnedAccount.getAddressList()).isEqualTo(new ArrayList<Address>());
    }

    @Test
    public void testGetAll() {
        Account mockAccount = createMockAccount();
        List<Account> mockAccountList = new ArrayList<>();
        mockAccountList.add(mockAccount);

        when(mockAccountRepository.findAll()).thenReturn(mockAccountList);

        List<Account> returnedAccountList = accountService.getAll();

        assertThat(returnedAccountList.get(0).getAccountId()).isEqualTo(1L);
        assertThat(returnedAccountList.get(0).getFirstName()).isEqualTo("first");
        assertThat(returnedAccountList.get(0).getLastName()).isEqualTo("last");
        assertThat(returnedAccountList.get(0).getEmail()).isEqualTo("name@email.com");

    }

    @Test
    public void testInsert() {
        Account mockAccount = createMockAccount();

        when(mockAccountRepository.save(any(Account.class))).thenReturn(mockAccount);

        Account returnedAccount = accountService.insert(mockAccount);

        assertThat(returnedAccount.getAccountId()).isEqualTo(1L);
        assertThat(returnedAccount.getFirstName()).isEqualTo("first");
        assertThat(returnedAccount.getLastName()).isEqualTo("last");
        assertThat(returnedAccount.getEmail()).isEqualTo("name@email.com");
        assertThat(returnedAccount.getAddressList()).isEqualTo(new ArrayList<Address>());
    }

    @Test
    public void testUpdate() {
        Account mockNewAccount = new Account();
        mockNewAccount.setFirstName("newfirst");
        mockNewAccount.setLastName("newlast");

        when(mockAccountRepository.save(any(Account.class))).then(returnsFirstArg());

        Account updatedAccount = accountService.update(5L, mockNewAccount);

        assertThat(updatedAccount.getAccountId()).isEqualTo(5L);
        assertThat(updatedAccount.getFirstName()).isEqualTo("newfirst");
        assertThat(updatedAccount.getLastName()).isEqualTo("newlast");
        assertThat(updatedAccount.getEmail()).isNull();
        assertThat(updatedAccount.getAddressList()).isNull();
    }

    @Test
    public void testDelete() {
        accountService.delete(1);
        verify(mockAccountRepository, times(1)).deleteById(anyLong());
    }

}