package com.company.config;

import com.company.adapter.out.persistence.entity.CompanyEntity;
import com.company.adapter.out.persistence.entity.OwnerEntity;
import com.company.adapter.out.persistence.jpaRepository.JpaCompanyRepository;
import com.company.adapter.out.persistence.jpaRepository.JpaOwnerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements ApplicationRunner {

    private final JpaCompanyRepository companyRepository;
    private final JpaOwnerRepository ownerRepository;

    public DataInitializer(JpaCompanyRepository companyRepository,
                           JpaOwnerRepository ownerRepository) {
        this.companyRepository = companyRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        CompanyEntity acme = new CompanyEntity();
        acme.setCvr("12345678");
        acme.setName("Acme A/S");
        acme.setAddress("Nørregade 1, 1165 København K");
        acme.setPhone("+4512345678");
        companyRepository.save(acme);

        CompanyEntity nordic = new CompanyEntity();
        nordic.setCvr("87654321");
        nordic.setName("Nordic Tech ApS");
        nordic.setAddress("Vesterbrogade 42, 1620 København V");
        nordic.setPhone("+4587654321");
        companyRepository.save(nordic);

        OwnerEntity owner1 = new OwnerEntity();
        owner1.setName("Lars Jensen");
        owner1.setAddress("Østerbrogade 10, 2100 København Ø");
        owner1.setCpr("1234567890");
        owner1.setCompany(acme);
        ownerRepository.save(owner1);

        OwnerEntity owner2 = new OwnerEntity();
        owner2.setName("Mette Nielsen");
        owner2.setAddress("Amagerbrogade 5, 2300 København S");
        owner2.setCpr("0987654321");
        owner2.setCompany(acme);
        ownerRepository.save(owner2);

        OwnerEntity owner3 = new OwnerEntity();
        owner3.setName("Anders Hansen");
        owner3.setAddress("Frederiksborggade 8, 1360 København K");
        owner3.setCpr("1122334455");
        owner3.setCompany(nordic);
        ownerRepository.save(owner3);
    }
}
