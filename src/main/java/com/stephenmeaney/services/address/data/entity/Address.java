package com.stephenmeaney.services.address.data.entity;

import com.stephenmeaney.services.account.data.entity.Account;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long addressId;

    private String Street;

    private String aptBuilding;

    private String city;

    private String stateProvince;

    private String zipPostalCode;

    private String country;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getAptBuilding() {
        return aptBuilding;
    }

    public void setAptBuilding(String aptBuilding) {
        this.aptBuilding = aptBuilding;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
