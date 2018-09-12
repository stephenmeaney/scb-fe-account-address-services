package com.stephenmeaney.services.account.data.repository;

import com.stephenmeaney.services.account.data.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAll();

    Account findById(long id);
}
