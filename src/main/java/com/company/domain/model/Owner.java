package com.company.domain.model;

public class Owner {
    private String name;
    private String address;
    private String cpr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Owner(String name, String address, String cpr) {
        if (!cpr.matches("\\d{10}"))
            throw new IllegalArgumentException("Invalid CPR");
        this.name = name;
        this.address = address;
        this.cpr = cpr;
    }
}
