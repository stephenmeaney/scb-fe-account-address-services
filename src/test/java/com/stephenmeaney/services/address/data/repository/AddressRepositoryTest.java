package com.stephenmeaney.services.address.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stephenmeaney.services.account.data.repository.AccountRepository;
import com.stephenmeaney.services.address.data.entity.Address;
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
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void testFindAll() {
        List<Address> addressList  = addressRepository.findAll();

        assertThat(addressList.get(0).getAddressId()).isEqualTo(1L);
        assertThat(addressList.get(0).getStreet()).isEqualTo("1 street");
        assertThat(addressList.get(0).getAptBuilding()).isEqualTo("1a");
        assertThat(addressList.get(0).getCity()).isEqualTo("city1");
        assertThat(addressList.get(0).getStateProvince()).isEqualTo("aa");
        assertThat(addressList.get(0).getZipPostalCode()).isEqualTo("11111");
        assertThat(addressList.get(0).getCountry()).isEqualTo("USA");

        assertThat(addressList.get(1).getAddressId()).isEqualTo(2L);
        assertThat(addressList.get(1).getStreet()).isEqualTo("2 street");
        assertThat(addressList.get(1).getAptBuilding()).isEqualTo("2a");
        assertThat(addressList.get(1).getCity()).isEqualTo("city2");
        assertThat(addressList.get(1).getStateProvince()).isEqualTo("bb");
        assertThat(addressList.get(1).getZipPostalCode()).isEqualTo("11112");
        assertThat(addressList.get(1).getCountry()).isEqualTo("USA");

    }

    @Test
    public void testFindById() {
        Address address  = addressRepository.findById(1L);

        assertThat(address.getAddressId()).isEqualTo(1L);
        assertThat(address.getStreet()).isEqualTo("1 street");
        assertThat(address.getAptBuilding()).isEqualTo("1a");
        assertThat(address.getCity()).isEqualTo("city1");
        assertThat(address.getStateProvince()).isEqualTo("aa");
        assertThat(address.getZipPostalCode()).isEqualTo("11111");
        assertThat(address.getCountry()).isEqualTo("USA");
    }

}