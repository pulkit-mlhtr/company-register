package com.company.application.dto;

import java.util.List;

public class CompanyResponse {

    private String cvr;
    private String address;
    private String phone;
    private List<OwnerResponse> owners;

    public CompanyResponse(String cvr, String address, String phone, List<OwnerResponse> owners) {
        this.cvr = cvr;
        this.address = address;
        this.phone = phone;
        this.owners = owners;
    }

    // Getters & Setters
    public String getCvr() { return cvr; }
    public void setCvr(String cvr) { this.cvr = cvr; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<OwnerResponse> getOwners() { return owners; }
    public void setOwners(List<OwnerResponse> owners) { this.owners = owners; }
}
