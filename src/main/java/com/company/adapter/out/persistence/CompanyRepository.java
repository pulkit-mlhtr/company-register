package com.company.adapter.out.persistence;

import com.company.adapter.out.persistence.mapper.CompanyMapper;
import com.company.adapter.out.persistence.jpaRepository.JpaCompanyRepository;
import com.company.domain.model.Company;
import com.company.domain.port.out.ICompanyRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CompanyRepository implements ICompanyRepository {
    private final JpaCompanyRepository repo;

    public CompanyRepository(JpaCompanyRepository repo) {
        this.repo = repo;
    }

    @Override
    public Company save(Company company) {
        return CompanyMapper.toDomain(repo.save(
                CompanyMapper.toEntity(company)
        ));
    }

    public Optional<Company> findByCvr(String cvr) {
        return repo.findById(cvr)
                .map(CompanyMapper::toDomain);
    }
}
