package com.company.adapter.out.external.mapper;

import com.company.adapter.out.external.model.Profitability;
import org.openapitools.client.model.ProfitabilityResponse;

public class ProfitabilityMapper {
    public static Profitability toModel(ProfitabilityResponse profitabilityResponse) {
        if(profitabilityResponse == null) {
            return null;
        }
        return new  Profitability(){{
            setProfitability(profitabilityResponse.getProfitability());
        }};
    }
}
