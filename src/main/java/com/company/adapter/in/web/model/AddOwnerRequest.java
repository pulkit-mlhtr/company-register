package com.company.adapter.in.web.model;

public class AddOwnerRequest {

    private String name;
    private String address;
    private String cpr;
    private String cvr;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCpr() { return cpr; }
    public void setCpr(String cpr) { this.cpr = cpr; }

    public String getCvr() { return cvr; }
    public void setCvr(String cvr) { this.cvr = cvr; }
}
