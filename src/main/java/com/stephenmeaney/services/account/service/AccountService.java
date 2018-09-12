package com.stephenmeaney.services.account.service;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getById(long id) {
        return accountRepository.findById(id);
    }
}
