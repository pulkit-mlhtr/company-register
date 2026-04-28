package com.company.adapter.out.persistence.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies")
public class CompanyEntity {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OwnerEntity> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerEntity> owners) {
        this.owners = owners;
    }

    @Id
    private String cvr;
    private String name;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OwnerEntity> owners;
}
