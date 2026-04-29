package com.company.application.test;

import com.company.adapter.in.web.model.OwnerResponse;
import com.company.adapter.out.persistence.entity.CompanyEntity;
import com.company.adapter.out.persistence.entity.OwnerEntity;
import com.company.adapter.out.persistence.jpaRepository.JpaOwnerRepository;
import com.company.application.service.OwnerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    @Mock
    private JpaOwnerRepository jpaOwnerRepository;

    @InjectMocks
    private OwnerService ownerService;

    private CompanyEntity testCompanyEntity;

    @Before
    public void setUp() {
        testCompanyEntity = new CompanyEntity();
        testCompanyEntity.setCvr("12345678");
        testCompanyEntity.setName("Test Company");
    }

    private OwnerEntity buildOwner(String name, String address, CompanyEntity company) {
        OwnerEntity entity = new OwnerEntity();
        entity.setName(name);
        entity.setAddress(address);
        entity.setCpr("1234567890");
        entity.setCompany(company);
        return entity;
    }

    @Test
    public void getAllOwners_whenNoOwners_returnsEmptyList() {
        when(jpaOwnerRepository.findAll()).thenReturn(Collections.emptyList());

        List<OwnerResponse> result = ownerService.getAllOwners();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(jpaOwnerRepository).findAll();
    }

    @Test
    public void getAllOwners_whenOwnerHasCompany_returnsCompanyName() {
        OwnerEntity entity = buildOwner("Jane Smith", "Main Street 5", testCompanyEntity);
        when(jpaOwnerRepository.findAll()).thenReturn(List.of(entity));

        List<OwnerResponse> result = ownerService.getAllOwners();

        assertEquals(1, result.size());
        assertEquals("Jane Smith", result.get(0).getName());
        assertEquals("Main Street 5", result.get(0).getAddress());
        assertEquals("Test Company", result.get(0).getCompanyName());
    }

    @Test
    public void getAllOwners_whenOwnerHasNoCompany_returnsNullCompanyName() {
        OwnerEntity entity = buildOwner("John Doe", "Side Street 3", null);
        when(jpaOwnerRepository.findAll()).thenReturn(List.of(entity));

        List<OwnerResponse> result = ownerService.getAllOwners();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertNull(result.get(0).getCompanyName());
    }

    @Test
    public void getAllOwners_withMixedOwners_mapsAllCorrectly() {
        OwnerEntity withCompany = buildOwner("Alice", "Elm St 1", testCompanyEntity);
        OwnerEntity withoutCompany = buildOwner("Bob", "Oak Ave 2", null);
        when(jpaOwnerRepository.findAll()).thenReturn(List.of(withCompany, withoutCompany));

        List<OwnerResponse> result = ownerService.getAllOwners();

        assertEquals(2, result.size());
        assertEquals("Test Company", result.get(0).getCompanyName());
        assertNull(result.get(1).getCompanyName());
    }
}
