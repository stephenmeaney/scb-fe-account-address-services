package com.stephenmeaney.services.account;

import com.stephenmeaney.services.account.api.AccountControllerTest;
import com.stephenmeaney.services.account.data.repository.AccountRepositoryIntegrationTest;
import com.stephenmeaney.services.account.data.repository.AccountRepositoryTest;
import com.stephenmeaney.services.account.service.AccountServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({AccountControllerTest.class, AccountRepositoryTest.class, AccountRepositoryIntegrationTest.class, AccountServiceTest.class})
public class AccountTestSuite {

}
