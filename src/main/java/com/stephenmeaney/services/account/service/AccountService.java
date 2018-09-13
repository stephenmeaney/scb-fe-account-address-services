package com.stephenmeaney.services.account.service;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.data.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getById(long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account insert(Account account) {
        return accountRepository.save(account);
    }

    public Account update(long id, Account newAccount) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newAccount.setAccountId(id);
        return accountRepository.save(newAccount);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
