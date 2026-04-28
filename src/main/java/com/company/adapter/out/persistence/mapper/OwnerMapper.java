package com.company.adapter.out.persistence.mapper;

import com.company.adapter.out.persistence.entity.OwnerEntity;
import com.company.application.dto.AddOwnerRequest;
import com.company.application.dto.CreateCompanyRequest;
import com.company.application.dto.OwnerResponse;
import com.company.domain.model.Company;
import com.company.domain.model.Owner;

public class OwnerMapper {
     public static Owner toDomain(OwnerEntity e) {
        return new Owner(
                e.getName(),
                e.getAddress(),
                e.getCpr()
        );
    }

    public static Owner toDomain(AddOwnerRequest e) {
        return new Owner(
                e.getName(),
                e.getAddress(),
                e.getCpr()
        );
    }

    public static OwnerEntity toEntity(Owner owner) {
        OwnerEntity entity = new OwnerEntity();
        entity.setCpr(owner.getCpr());
        entity.setName(owner.getName());
        entity.setAddress(owner.getAddress());
        return entity;
    }

    public static OwnerResponse toResponse(Owner owner, String companyName) {
         return new OwnerResponse(owner.getName(), owner.getAddress(),  companyName);
    }
}
