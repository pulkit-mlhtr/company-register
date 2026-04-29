package com.company.domain.port.in;

import com.company.adapter.out.external.model.Profitability;
import com.company.adapter.in.web.model.AddOwnerRequest;
import com.company.adapter.in.web.model.OwnerResponse;
import com.company.domain.model.Company;

public interface ICompanyService {
    Company createCompany(Company company);
    Company getCompany(String cvr);
    Profitability getCompanyProfitability(String cvr);
    OwnerResponse addOwner(AddOwnerRequest request);
}
