package com.company.adapter.in.web.controller;

import com.company.adapter.out.external.mapper.ProfitabilityMapper;
import com.company.adapter.out.persistence.mapper.CompanyMapper;
import com.company.adapter.in.web.model.AddOwnerRequest;
import com.company.adapter.in.web.model.CompanyResponse;
import com.company.adapter.in.web.model.CreateCompanyRequest;
import com.company.adapter.in.web.model.OwnerResponse;
import com.company.domain.port.in.ICompanyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.openapitools.client.model.ProfitabilityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final ICompanyService _companyService;

    public CompanyController(ICompanyService companyService) {
        this._companyService = companyService;
    }

    @Operation(summary = "Create a new company")
    @PostMapping
    public ResponseEntity<CompanyResponse> create(
            @Valid @RequestBody CreateCompanyRequest request) {

        return ResponseEntity.ok(
                CompanyMapper.toResponse(_companyService.createCompany(CompanyMapper.toDomain(request)))
        );
    }

    @Operation(summary = "Add an owner to a company by CVR")
    @PostMapping("/{cvr}/owners")
    public OwnerResponse addOwner(
            @PathVariable String cvr,
            @Valid @RequestBody AddOwnerRequest request) {
        request.setCvr(cvr);
        return _companyService.addOwner(request);
    }

    @Operation(summary = "Get a company by CVR")
    @GetMapping("/{cvr}")
    public ResponseEntity<CompanyResponse> get(@PathVariable String cvr) {
        return ResponseEntity.ok(CompanyMapper.toResponse(_companyService.getCompany(cvr)));
    }

    @Operation(summary = "Get company profitability by CVR")
    @GetMapping("/{cvr}/profit")
    public ResponseEntity<ProfitabilityResponse> getProfitability(@PathVariable String cvr) {
        var response = ProfitabilityMapper.toResponse(_companyService.getCompanyProfitability(cvr));

        response.setStatus(String.format("%s is %s:", cvr, response.getStatus()));
        return ResponseEntity.ok(response);
    }
}
