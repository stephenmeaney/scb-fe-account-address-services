package com.stephenmeaney.services.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.service.AccountService;
import com.stephenmeaney.services.address.data.entity.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class, secure = false)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper mapper;

    private Account createMockAccount(long num) {
        Account mockAccount = new Account();
        mockAccount.setAccountId(num);
        mockAccount.setFirstName("first" + num);
        mockAccount.setLastName("last" + num);
        mockAccount.setEmail("email" + num);
        mockAccount.setAddressList(new ArrayList<Address>());

        return mockAccount;
    }

    @Test
    public void testGetAll() throws Exception{

        List<Account> mockAccountList = new ArrayList<>();
        mockAccountList.add(createMockAccount(1));
        mockAccountList.add(createMockAccount(2));

        when(accountService.getAll()).thenReturn(mockAccountList);

        mockMvc.perform(get("/api/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2) ))
                .andExpect(jsonPath("$[0].*", hasSize(5) ))
                .andExpect(jsonPath("$[0].accountId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("first1"))
                .andExpect(jsonPath("$[0].lastName").value("last1"))
                .andExpect(jsonPath("$[0].email").value("email1"))
                .andExpect(jsonPath("$[0].addressList", hasSize(0)));


    }

    @Test
    public void testGetById() throws Exception {

        Account mockAccount = createMockAccount(3);

        when(accountService.getById(3)).thenReturn(mockAccount);

        mockMvc.perform(get("/api/v1/accounts/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5) ))
                .andExpect(jsonPath("$.accountId").value(3))
                .andExpect(jsonPath("$.firstName").value("first3"))
                .andExpect(jsonPath("$.lastName").value("last3"))
                .andExpect(jsonPath("$.email").value("email3"))
                .andExpect(jsonPath("$.addressList", hasSize(0)));
    }

    @Test
    public void testInsert() throws Exception {

        Account mockAccount = createMockAccount(4);

        when(accountService.insert(any(Account.class))).thenReturn(mockAccount);

        mockMvc.perform(post("/api/v1/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockAccount)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*", hasSize(5) ))
                .andExpect(jsonPath("$.accountId").value(4))
                .andExpect(jsonPath("$.firstName").value("first4"))
                .andExpect(jsonPath("$.lastName").value("last4"))
                .andExpect(jsonPath("$.email").value("email4"))
                .andExpect(jsonPath("$.addressList", hasSize(0)));
    }

    @Test
    public void testUpdate() throws Exception {

        Account mockAccount = createMockAccount(1);

        when(accountService.update(anyLong(), any(Account.class))).thenReturn(mockAccount);

        mockMvc.perform(put("/api/v1/accounts/2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockAccount)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/accounts/3"))
                .andExpect(status().isNoContent());

    }
}