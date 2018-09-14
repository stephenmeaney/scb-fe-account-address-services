package com.stephenmeaney.services.address.api;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.service.AccountService;
import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("")
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable long id) {
        return addressService.getById(id);
    }

}
