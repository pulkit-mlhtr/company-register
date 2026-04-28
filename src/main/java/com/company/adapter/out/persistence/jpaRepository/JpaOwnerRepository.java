package com.company.adapter.out.persistence.jpaRepository;

import com.company.adapter.out.persistence.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOwnerRepository extends JpaRepository<OwnerEntity, Long> {
    List<OwnerEntity> findByCompanyCvr(String cvr);
}
