package com.company.application.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCompanyRequest {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCvr() {
        return cvr;
    }

    public String getName() {
        return name;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "CVR is required")
    private String cvr;

    @NotBlank(message = "Company must have name")
    private String name;

}
