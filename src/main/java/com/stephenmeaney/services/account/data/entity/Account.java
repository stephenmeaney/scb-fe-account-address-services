package com.stephenmeaney.services.account.data.entity;

import com.stephenmeaney.services.address.data.entity.Address;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private long accountId;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Address> addressList;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
