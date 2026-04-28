package com.company.adapter.out.external.mapper;

import com.company.adapter.out.external.model.Profitability;
import org.openapitools.client.model.ProfitabilityResponse;

import java.math.BigDecimal;

public class ProfitabilityMapper {
    public static Profitability toModel(ProfitabilityResponse profitabilityResponse) {
        if(profitabilityResponse == null) {
            return null;
        }
        var profitability = new Profitability();
        profitability.setProfitability(profitabilityResponse.getProfitability());
        profitability.setStatus(resolveStatus(profitabilityResponse.getNetProfit()));
        profitability.setRevenue(profitabilityResponse.getRevenue());
        profitability.setNetProfit(profitabilityResponse.getNetProfit());

        return profitability;
    }

    public static ProfitabilityResponse toResponse(Profitability profitability){
        if(profitability == null) {
            return null;
        }
        ProfitabilityResponse response = new ProfitabilityResponse();
        response.setStatus(resolveStatus(profitability.getNetProfit()));
        response.setProfitability(profitability.getProfitability());
        response.setRevenue(profitability.getRevenue());
        response.setNetProfit(profitability.getNetProfit());
        return response;
    }

    private static String resolveStatus(BigDecimal netProfit) {
        if (netProfit == null) {
            return "LOSS";
        }
        if (netProfit.compareTo(new BigDecimal("100")) > 0) {
            return "PROFITABLE";
        }
        if (netProfit.compareTo(BigDecimal.ZERO) < 0) {
            return "LOSS";
        }
        return "BREAKEVEN";
    }
}
