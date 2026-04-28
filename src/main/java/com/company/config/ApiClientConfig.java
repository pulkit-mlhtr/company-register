package com.company.config;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.ProfitabilityApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfig {
    @Bean
    public ProfitabilityApi profitabilityApi() {
        // ApiClient is a helper generated in your 'gen' folder
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://profitability-service.local:8080");

        return new ProfitabilityApi(apiClient);
    }
}
