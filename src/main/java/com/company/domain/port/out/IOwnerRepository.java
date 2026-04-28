package com.company.domain.port.out;

import com.company.domain.model.Company;
import com.company.domain.model.Owner;

import java.util.Optional;

public interface IOwnerRepository {
    Owner save(Owner owner, Company company);
    Optional<Owner> findByCvr(String cvr);
}
