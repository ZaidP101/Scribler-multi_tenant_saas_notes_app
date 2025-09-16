package com.notes.app.Scribler.Service;

import com.notes.app.Scribler.DTO.AddTenantDto;
import com.notes.app.Scribler.DTO.TenantDto;
import jakarta.validation.Valid;

import java.util.List;

public interface TenantService {
    TenantDto addTenant(@Valid AddTenantDto addTenantDto);

    TenantDto upgradeTenant(String slug);

    List<TenantDto> getAllRenants();

    TenantDto getTenantBySlug(String slug);
}
