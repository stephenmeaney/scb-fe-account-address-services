package com.stephenmeaney.services.account.api;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.service.AccountService;
import com.stephenmeaney.services.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable long id) {
        return accountService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<Account> insert(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.insert(account), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable long id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        accountService.delete(id);
    }

}
