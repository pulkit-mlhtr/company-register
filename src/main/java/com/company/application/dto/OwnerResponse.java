package com.company.application.dto;

public class OwnerResponse {

    private String name;
    private String address;
    private String companyName;

    public OwnerResponse(String name, String address, String companyName) {
        this.name = name;
        this.address = address;
        this.companyName = companyName;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
