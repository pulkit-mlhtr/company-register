package com.company.adapter.in.web.controller;

import com.company.application.dto.OwnerResponse;
import com.company.domain.port.in.IOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final IOwnerService ownerService;

    public OwnerController(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(summary = "Get all owners")
    @GetMapping
    public List<OwnerResponse> getAll() {
        return ownerService.getAllOwners();
    }
}
