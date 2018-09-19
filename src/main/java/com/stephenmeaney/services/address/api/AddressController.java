package com.stephenmeaney.services.address.api;

import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{accountId}/addresses")
    public ResponseEntity<List<Address>> getAll(@PathVariable long accountId) {

        return addressService.getAll(accountId);
    }

    @GetMapping("/{accountId}/addresses/{addressId}")
    public ResponseEntity<Address> getById(@PathVariable long addressId, @PathVariable long accountId) {

        return addressService.getById(addressId, accountId);
    }

    @PostMapping("/{accountId}/addresses")
    public ResponseEntity<Address> insert(@RequestBody Address address, @PathVariable long accountId) {

        return addressService.insert(address, accountId);
    }

    @PutMapping("/{accountId}/addresses/{addressId}")
    public ResponseEntity<Address> update(@RequestBody Address address, @PathVariable long addressId, @PathVariable long accountId) {

        return addressService.update(address, addressId, accountId);
    }

    @DeleteMapping("/{accountId}/addresses/{addressId}")
    public ResponseEntity<Address> delete(@PathVariable long addressId, @PathVariable long accountId) {

        return addressService.delete(addressId, accountId);
    }

}
