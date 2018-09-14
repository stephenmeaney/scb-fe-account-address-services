package com.stephenmeaney.services.address.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AddressController.class, secure = false)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Autowired
    private ObjectMapper mapper;

    public Address createMockAddress(long num) {
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

    @Test
    public void testGetAll() throws Exception{

        List<Address> mockAddressList = new ArrayList<>();
        mockAddressList.add(createMockAddress(1));
        mockAddressList.add(createMockAddress(2));

        when(addressService.getAll()).thenReturn(mockAddressList);

        mockMvc.perform(get("/api/v1/addresses"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {

        Address mockAddress = createMockAddress(3);

        when(addressService.getById(3)).thenReturn(mockAddress);

        mockMvc.perform(get("/api/v1/addresses/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsert() throws Exception {

        Address mockAddress = createMockAddress(4);

        when(addressService.insert(any(Address.class))).thenReturn(mockAddress);

        mockMvc.perform(post("/api/v1/addresses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockAddress)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        Address mockAddress = createMockAddress(1);

        when(addressService.update(anyLong(), any(Address.class))).thenReturn(mockAddress);

        mockMvc.perform(put("/api/v1/addresses/2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockAddress)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/addresses/3"))
                .andExpect(status().isNoContent());
    }


}