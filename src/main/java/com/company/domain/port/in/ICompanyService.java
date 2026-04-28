package com.company.domain.port.in;

import com.company.adapter.out.external.model.Profitability;
import com.company.application.dto.AddOwnerRequest;
import com.company.application.dto.OwnerResponse;
import com.company.domain.model.Company;

public interface ICompanyService {
    Company createCompany(Company company);
    Company getCompany(String cvr);
    Profitability getCompanyProfitability(String cvr);
    OwnerResponse addOwner(AddOwnerRequest request);
}
