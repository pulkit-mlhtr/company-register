package com.company.domain.model;

import com.company.adapter.out.external.model.Profitability;

import java.util.List;

public class Company {

    private final String cvr;
    private String name;
    private String address;
    private String phone;
    private List<Owner> owners;
    private Profitability  profitability;

    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public Profitability getProfitability() {
        return profitability;
    }
    public String getPhone() {
        return phone;
    }
    public List<Owner> getOwners() {
        return owners;
    }
    public String getCvr() {
        return cvr;
    }
    
    public void setProfitability(Profitability profitability) {
        this.profitability = profitability;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Company(String cvr, String address, String phone,String name, List<Owner> owners) {
        if (cvr == null || cvr.length() != 8)
            throw new IllegalArgumentException("Invalid CVR");

        this.cvr = cvr;
        this.address = address;
        this.phone = phone;
        this.owners = owners;
        this.name = name;
    }
}
