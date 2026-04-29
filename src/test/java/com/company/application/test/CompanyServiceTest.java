package com.company.application.test;

import com.company.adapter.in.web.model.AddOwnerRequest;
import com.company.adapter.in.web.model.OwnerResponse;
import com.company.adapter.out.external.model.Profitability;
import com.company.application.service.CompanyService;
import com.company.domain.model.Company;
import com.company.domain.model.Owner;
import com.company.domain.port.out.ICompanyRepository;
import com.company.domain.port.out.IOwnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openapitools.client.api.ProfitabilityApi;
import org.openapitools.client.model.ProfitabilityResponse;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    private ICompanyRepository companyRepository;

    @Mock
    private IOwnerRepository ownerRepository;


    private ProfitabilityApi profitabilityApi;

    @InjectMocks
    private CompanyService companyService;

    private Company testCompany;

    @Before
    public void setUp() {
        testCompany = new Company("12345678", "Test Street 1", "11223344", "Test Company", Collections.emptyList());
        profitabilityApi = new ProfitabilityApi();
    }

    // createCompany

    @Test
    public void createCompany_savesAndReturnsCompany() {
        when(companyRepository.save(testCompany)).thenReturn(testCompany);

        Company result = companyService.createCompany(testCompany);

        assertNotNull(result);
        assertEquals("12345678", result.getCvr());
        assertEquals("Test Company", result.getName());
        verify(companyRepository).save(testCompany);
    }

    // getCompany

    @Test
    public void getCompany_whenFound_returnsCompany() {
        when(companyRepository.findByCvr("12345678")).thenReturn(Optional.of(testCompany));

        Company result = companyService.getCompany("12345678");

        assertNotNull(result);
        assertEquals("12345678", result.getCvr());
    }

    @Test
    public void getCompany_whenNotFound_returnsNull() {
        when(companyRepository.findByCvr("99999999")).thenReturn(Optional.empty());

        Company result = companyService.getCompany("99999999");

        assertNull(result);
    }

    // getCompanyProfitability

    @Test
    public void getCompanyProfitability_returnsMappedProfitability() {
        ProfitabilityResponse response = new ProfitabilityResponse();
        response.setProfitability(0.15);
        response.setRevenue(new BigDecimal("100000"));
        response.setNetProfit(new BigDecimal("150"));

        when(profitabilityApi.getProfitabilityByCvr("12345678")).thenReturn(response);

        Profitability result = companyService.getCompanyProfitability("12345678");

        assertNotNull(result);
        assertEquals("PROFITABLE", result.getStatus());
        assertEquals(new BigDecimal("150"), result.getNetProfit());
        assertEquals(new BigDecimal("100000"), result.getRevenue());
    }

    @Test
    public void getCompanyProfitability_whenNetProfitIsNegative_returnsLossStatus() {
        ProfitabilityResponse response = new ProfitabilityResponse();
        response.setProfitability(-0.05);
        response.setNetProfit(new BigDecimal("-500"));

        when(profitabilityApi.getProfitabilityByCvr("12345678")).thenReturn(response);

        Profitability result = companyService.getCompanyProfitability("12345678");

        assertNotNull(result);
        assertEquals("LOSS", result.getStatus());
    }

    @Test
    public void getCompanyProfitability_whenNetProfitIsBreakeven_returnsBreakevenStatus() {
        ProfitabilityResponse response = new ProfitabilityResponse();
        response.setProfitability(0.0);
        response.setNetProfit(new BigDecimal("50"));

        when(profitabilityApi.getProfitabilityByCvr("12345678")).thenReturn(response);

        Profitability result = companyService.getCompanyProfitability("12345678");

        assertNotNull(result);
        assertEquals("BREAKEVEN", result.getStatus());
    }

    @Test
    public void getCompanyProfitability_whenApiReturnsNull_returnsNull() {
        when(profitabilityApi.getProfitabilityByCvr("12345678")).thenReturn(null);

        Profitability result = companyService.getCompanyProfitability("12345678");

        assertNull(result);
    }

    // addOwner

    @Test
    public void addOwner_returnsOwnerResponse() {
        AddOwnerRequest request = new AddOwnerRequest();
        request.setName("John Doe");
        request.setAddress("Owner Street 2");
        request.setCpr("1234567890");
        request.setCvr("12345678");

        Owner savedOwner = new Owner("John Doe", "Owner Street 2", "1234567890");

        when(companyRepository.findByCvr("12345678")).thenReturn(Optional.of(testCompany));
        when(ownerRepository.save(any(Owner.class), eq(testCompany))).thenReturn(savedOwner);

        OwnerResponse result = companyService.addOwner(request);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("Owner Street 2", result.getAddress());
        assertEquals("Test Company", result.getCompanyName());
    }

    @Test(expected = NullPointerException.class)
    public void addOwner_whenCompanyNotFound_throwsNullPointerException() {
        AddOwnerRequest request = new AddOwnerRequest();
        request.setName("John Doe");
        request.setAddress("Owner Street 2");
        request.setCpr("1234567890");
        request.setCvr("99999999");

        when(companyRepository.findByCvr("99999999")).thenReturn(Optional.empty());

        companyService.addOwner(request);
    }
}
