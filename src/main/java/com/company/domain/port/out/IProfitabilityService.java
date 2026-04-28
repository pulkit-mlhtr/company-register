package com.company.domain.port.out;

import com.company.adapter.out.external.model.Profitability;

public interface IProfitabilityService {
    Profitability getProfitability(String cvr);
}