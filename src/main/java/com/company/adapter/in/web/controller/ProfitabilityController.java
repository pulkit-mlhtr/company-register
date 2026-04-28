package com.company.adapter.in.web.controller;

import com.company.adapter.out.external.mapper.ProfitabilityMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.openapitools.client.model.ProfitabilityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/profitability")
public class ProfitabilityController {
    @Operation(summary = "Get company profitability by CVR")
    @GetMapping("/{cvr}")
    public ResponseEntity<ProfitabilityResponse> getProfitability(@PathVariable String cvr) {
        ProfitabilityResponse response = new ProfitabilityResponse();
        response.setProfitability(12.5);
        response.setRevenue(new BigDecimal("1000000"));
        response.setNetProfit(new BigDecimal("125000"));
        return ResponseEntity.ok(response);
    }
}
