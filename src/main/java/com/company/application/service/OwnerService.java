package com.company.application.service;

import com.company.adapter.out.persistence.jpaRepository.JpaOwnerRepository;
import com.company.adapter.in.web.model.OwnerResponse;
import com.company.domain.port.in.IOwnerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService implements IOwnerService {

    private final JpaOwnerRepository ownerRepository;

    public OwnerService(JpaOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<OwnerResponse> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(e -> new OwnerResponse(
                        e.getName(),
                        e.getAddress(),
                        e.getCompany() != null ? e.getCompany().getName() : null
                ))
                .toList();
    }
}
