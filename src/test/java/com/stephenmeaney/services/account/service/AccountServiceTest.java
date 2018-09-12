package com.stephenmeaney.services.account.service;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.data.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository mockAccountRepository;

    @InjectMocks
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setFirstName("firstname");
        mockAccount.setLastName("lastname");
        mockAccount.setEmail("name@email.com");

        when(mockAccountRepository.findById(anyLong())).thenReturn(mockAccount);

        Account returnedAccount = accountService.getById(1L);

        assertThat(returnedAccount.getAccountId()).isEqualTo(1L);
        assertThat(returnedAccount.getFirstName()).isEqualTo("firstname");
        assertThat(returnedAccount.getLastName()).isEqualTo("lastname");
        assertThat(returnedAccount.getEmail()).isEqualTo("name@email.com");
    }


}