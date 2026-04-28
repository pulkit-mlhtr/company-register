package com.company.domain.port.in;

import com.company.application.dto.OwnerResponse;

import java.util.List;

public interface IOwnerService {
    List<OwnerResponse> getAllOwners();
}
