package com.company.domain.port.in;

import com.company.adapter.in.web.model.OwnerResponse;

import java.util.List;

public interface IOwnerService {
    List<OwnerResponse> getAllOwners();
}
