package com.stephenmeaney.services.address.data.repository;

import com.stephenmeaney.services.address.data.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    private Address createMockAddress() {
        Address mockAddress = new Address();
        mockAddress.setStreet("street1");
        mockAddress.setAptBuilding("1b");
        mockAddress.setCity("town1");
        mockAddress.setStateProvince("state1");
        mockAddress.setZipPostalCode("10001");
        mockAddress.setCountry("country1");

        return mockAddress;
    }

    @Test
    public void testPersistData() {
        Address mockAddress = createMockAddress();

        if (addressRepository.findById(1L) != null) {
            addressRepository.deleteById(1L);
        }

        entityManager.persistAndFlush(mockAddress);

        Address foundAddress = addressRepository.findById(1L);

        assertThat(foundAddress.getAddressId()).isEqualTo(1L);
        assertThat(foundAddress.getStreet()).isEqualTo("street1");
        assertThat(foundAddress.getAptBuilding()).isEqualTo("1b");
        assertThat(foundAddress.getCity()).isEqualTo("town1");
        assertThat(foundAddress.getStateProvince()).isEqualTo("state1");
        assertThat(foundAddress.getZipPostalCode()).isEqualTo("10001");
        assertThat(foundAddress.getCountry()).isEqualTo("country1");

    }
}
