package com.stephenmeaney.services.address;

import com.stephenmeaney.services.address.api.AddressControllerTest;
import com.stephenmeaney.services.address.data.repository.AddressRepositoryIntegrationTest;
import com.stephenmeaney.services.address.data.repository.AddressRepositoryTest;
import com.stephenmeaney.services.address.service.AddressServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({AddressControllerTest.class, AddressRepositoryTest.class, AddressRepositoryIntegrationTest.class, AddressServiceTest.class})
public class AddressTestSuite {

}
