package com.company.domain.port.out;
import com.company.domain.model.Company;
import java.util.Optional;

public interface ICompanyRepository {
    Company save(Company company);
    Optional<Company> findByCvr(String cvr);
}
