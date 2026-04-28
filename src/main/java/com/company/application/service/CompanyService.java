package com.company.application.service;

import com.company.adapter.out.external.mapper.ProfitabilityMapper;
import com.company.adapter.out.external.model.Profitability;
import com.company.adapter.out.persistence.mapper.OwnerMapper;
import com.company.application.dto.AddOwnerRequest;
import com.company.application.dto.OwnerResponse;
import com.company.domain.model.Company;
import com.company.domain.model.Owner;
import com.company.domain.port.in.ICompanyService;
import com.company.domain.port.out.ICompanyRepository;
import com.company.domain.port.out.IOwnerRepository;
import org.openapitools.client.api.ProfitabilityApi;
import org.openapitools.client.model.ProfitabilityResponse;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {
    private final ICompanyRepository _companyRepository;
    private final IOwnerRepository _ownerRepository;
    private final ProfitabilityApi _profitabilityService;

    public CompanyService(ICompanyRepository companyRepository,
                          IOwnerRepository  ownerRepository,
                          ProfitabilityApi profitabilityService) {
        this._companyRepository = companyRepository;
        this._profitabilityService = profitabilityService;
        this._ownerRepository = ownerRepository;
    }

    @Override
    public Company createCompany(Company company) {
        return _companyRepository.save(company);
    }

    @Override
    public Company getCompany(String cvr) {

        return _companyRepository.findByCvr(cvr)
                .orElse(null);
    }

    @Override
    public Profitability getCompanyProfitability(String cvr) {

        ProfitabilityResponse response = _profitabilityService.getProfitabilityByCvr(cvr);

        return ProfitabilityMapper.toModel(response);
    }

    @Override
    public OwnerResponse addOwner(AddOwnerRequest request) {
        Company company = getCompany(request.getCvr());

        Owner toBeSaved = OwnerMapper.toDomain(request);

        return OwnerMapper.toResponse(_ownerRepository.save(toBeSaved,company),company.getName());
    }
}
