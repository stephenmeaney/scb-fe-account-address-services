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

    @PostMapping("")
    public ResponseEntity<Address> insert(@RequestBody Address address) {
        return new ResponseEntity<>(addressService.insert(address), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable long id, @RequestBody Address address) {
        return addressService.update(id, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        addressService.delete(id);
    }

}
