package com.company.adapter.out.persistence;

import com.company.adapter.out.persistence.jpaRepository.JpaOwnerRepository;
import com.company.adapter.out.persistence.mapper.CompanyMapper;
import com.company.adapter.out.persistence.mapper.OwnerMapper;
import com.company.domain.model.Company;
import com.company.domain.model.Owner;
import com.company.domain.port.out.IOwnerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OwnerRepository implements IOwnerRepository {
    private final JpaOwnerRepository repo;

    public OwnerRepository(JpaOwnerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Owner save(Owner owner, Company company) {
        var toBeSaved = OwnerMapper.toEntity(owner);

        toBeSaved.setCompany(CompanyMapper.toEntity(company));

        return OwnerMapper.toDomain(repo.save(toBeSaved));
    }

    @Override
    public Optional<Owner> findByCvr(String cvr) {
        return repo.findByCompanyCvr(cvr).stream().findFirst()
                .map(OwnerMapper::toDomain);
    }
}
