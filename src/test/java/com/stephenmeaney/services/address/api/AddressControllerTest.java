package com.stephenmeaney.services.address.api;

import com.stephenmeaney.services.account.api.AccountController;
import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.service.AddressService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public Address createMockAddress(String num) {
        Address mockAddress = new Address();
        mockAddress.setAddressId(1L);
        mockAddress.setStreet("street" + num);
        mockAddress.setAptBuilding("apt" + num);
        mockAddress.setCity("city" + num);
        mockAddress.setStateProvince("state" + num);
        mockAddress.setZipPostalCode("zip" + num);
        mockAddress.setCountry("country"  + num);

        return mockAddress;
    }

}