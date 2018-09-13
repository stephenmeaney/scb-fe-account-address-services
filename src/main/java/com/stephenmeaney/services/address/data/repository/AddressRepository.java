package com.stephenmeaney.services.address.data.repository;

import com.stephenmeaney.services.address.data.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findAll();

    Address findById(long id);
}
