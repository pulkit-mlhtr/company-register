package com.company.adapter.in.web.controller;

import com.company.adapter.out.persistence.mapper.CompanyMapper;
import com.company.application.dto.AddOwnerRequest;
import com.company.application.dto.CompanyResponse;
import com.company.application.dto.CreateCompanyRequest;
import com.company.application.dto.OwnerResponse;
import com.company.domain.port.in.ICompanyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
}
