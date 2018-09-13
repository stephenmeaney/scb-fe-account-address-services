package com.stephenmeaney.services.address.service;

import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getById(long id) {
        return addressRepository.findById(id);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address insert(Address account) {
        return addressRepository.save(account);
    }

    public Address update(long id, Address newAccount) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newAccount.setAddressId(id);
        return addressRepository.save(newAccount);
    }

    public void delete(long id) {
        addressRepository.deleteById(id);
    }
}
