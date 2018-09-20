package com.stephenmeaney.services.address.service;

import com.stephenmeaney.services.account.data.entity.Account;
import com.stephenmeaney.services.account.data.repository.AccountRepository;
import com.stephenmeaney.services.address.data.entity.Address;
import com.stephenmeaney.services.address.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, AccountRepository accountRepository) {
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<List<Address>> getAll(long accountId) {
        if (accountRepository.findById(accountId) != null) {
            return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Address> getById(long addressId, long accountId) {
        if (accountRepository.findById(accountId) != null) {
            return new ResponseEntity<>(addressRepository.findById(addressId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Address> insert(Address address, long accountId) {
        Account account = accountRepository.findById(accountId);
        if (account != null) {
            address.setAccount(account);
            return new ResponseEntity<>(addressRepository.save(address), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Address> update(Address newAddress, long addressId, long accountId) {
        // check contract for "id not found" behavior and how to handle incomplete entity
        if (accountRepository.findById(accountId) != null) {
            newAddress.setAddressId(addressId);
            System.out.println(addressId);
            return new ResponseEntity<>(addressRepository.save(newAddress), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Address> delete(long addressId, long accountId) {
        if (accountRepository.findById(accountId) != null) {
            addressRepository.deleteById(addressId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
