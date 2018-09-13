package com.stephenmeaney.services.address.service;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.data.repository.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository mockAddressRepository;

    @InjectMocks
    private AddressService addressService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public Address createMockAddress() {
        Address mockAddress = new Address();
        mockAddress.setAddressId(1L);
        mockAddress.setStreet("street1");
        mockAddress.setAptBuilding("1b");
        mockAddress.setCity("city1");
        mockAddress.setStateProvince("state1");
        mockAddress.setZipPostalCode("10001");
        mockAddress.setCountry("country1");

        return mockAddress;
    }

    @Test
    public void testGetById() {
        Address mockAddress = createMockAddress();

        when(mockAddressRepository.findById(anyLong())).thenReturn(mockAddress);

        Address returnedAddress = addressService.getById(1L);

        assertThat(returnedAddress.getAddressId()).isEqualTo(1L);
        assertThat(returnedAddress.getStreet()).isEqualTo("street1");
        assertThat(returnedAddress.getAptBuilding()).isEqualTo("1b");
        assertThat(returnedAddress.getCity()).isEqualTo("city1");
        assertThat(returnedAddress.getStateProvince()).isEqualTo("state1");
        assertThat(returnedAddress.getZipPostalCode()).isEqualTo("10001");
        assertThat(returnedAddress.getCountry()).isEqualTo("country1");
    }

    @Test
    public void testGetAll() {
        Address mockAddress = createMockAddress();
        List<Address> mockAddressList = new ArrayList<>();
        mockAddressList.add(mockAddress);

        when(mockAddressRepository.findAll()).thenReturn(mockAddressList);

        List<Address> returnedAddressList = addressService.getAll();

        assertThat(returnedAddressList.get(0).getAddressId()).isEqualTo(1L);
        assertThat(returnedAddressList.get(0).getStreet()).isEqualTo("street1");
        assertThat(returnedAddressList.get(0).getAptBuilding()).isEqualTo("1b");
        assertThat(returnedAddressList.get(0).getCity()).isEqualTo("city1");
        assertThat(returnedAddressList.get(0).getStateProvince()).isEqualTo("state1");
        assertThat(returnedAddressList.get(0).getZipPostalCode()).isEqualTo("10001");
        assertThat(returnedAddressList.get(0).getCountry()).isEqualTo("country1");
    }

    @Test
    public void testInsert() {
        Address mockAddress = createMockAddress();

        when(mockAddressRepository.save(any(Address.class))).thenReturn(mockAddress);

        Address returnedAddress = addressService.insert(mockAddress);

        assertThat(returnedAddress.getAddressId()).isEqualTo(1L);
        assertThat(returnedAddress.getStreet()).isEqualTo("street1");
        assertThat(returnedAddress.getAptBuilding()).isEqualTo("1b");
        assertThat(returnedAddress.getCity()).isEqualTo("city1");
        assertThat(returnedAddress.getStateProvince()).isEqualTo("state1");
        assertThat(returnedAddress.getZipPostalCode()).isEqualTo("10001");
        assertThat(returnedAddress.getCountry()).isEqualTo("country1");
    }

    @Test
    public void testUpdate() {
        Address mockNewAddress = new Address();
        mockNewAddress.setStreet("newstreet");
        mockNewAddress.setCity("newcity");

        when(mockAddressRepository.save(any(Address.class))).then(returnsFirstArg());

        Address updatedAddress = addressService.update(5L, mockNewAddress);

        assertThat(updatedAddress.getAddressId()).isEqualTo(5L);
        assertThat(updatedAddress.getStreet()).isEqualTo("newstreet");
        assertThat(updatedAddress.getAptBuilding()).isNull();
        assertThat(updatedAddress.getCity()).isEqualTo("newcity");
        assertThat(updatedAddress.getStateProvince()).isNull();
        assertThat(updatedAddress.getZipPostalCode()).isNull();
        assertThat(updatedAddress.getCountry()).isNull();
    }

    @Test
    public void testDelete() {
        addressService.delete(1L);
        verify(mockAddressRepository, times(1)).deleteById(anyLong());
    }
}