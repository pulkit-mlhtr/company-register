package com.company.adapter.out.persistence.jpaRepository;

import com.company.adapter.out.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, String> {}
