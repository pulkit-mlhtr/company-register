package com.company.adapter.out.persistence.mapper;

import com.company.adapter.out.persistence.entity.CompanyEntity;
import com.company.adapter.in.web.model.CompanyResponse;
import com.company.adapter.in.web.model.CreateCompanyRequest;
import com.company.domain.model.Company;
import com.company.domain.model.Owner;

import java.util.List;
import java.util.Optional;

public class CompanyMapper {
    public static Company toDomain(CompanyEntity e) {
        if (e == null) {
            return null;
        }

        return new Company(
                e.getCvr(),
                e.getAddress(),
                e.getPhone(),
                e.getName(),
                Optional.ofNullable(e.getOwners())
                        .orElse(List.of())
                        .stream()
                        .map(OwnerMapper::toDomain)
                        .toList()
        );
    }

    public static Company toDomain(CreateCompanyRequest e) {
        return new Company(
                e.getCvr(),
                e.getAddress(),
                e.getPhone(),
                e.getName(),
                null
        );
    }

    public static CompanyEntity toEntity(Company company) {
        if (company == null) {
            return null;
        }
        return new CompanyEntity(){{
            setCvr(company.getCvr());
            setName(company.getName());
            setAddress(company.getAddress());
            setPhone(company.getPhone());
            setOwners(Optional.ofNullable(company.getOwners())
                    .orElse(List.of())
                    .stream()
                    .map(OwnerMapper::toEntity)
                    .toList());
        }};
    }

    public static CompanyResponse toResponse(Company company) {
        if (company == null) {
            return null;
        }
        return new CompanyResponse(company.getCvr(),
                company.getAddress(),
                company.getPhone(),
                Optional.ofNullable(company.getOwners())
                        .orElse(List.of())
                        .stream()
                        .map((Owner owner) -> OwnerMapper.toResponse(owner,company.getName()))
                        .toList()

        );
    }
}