package com.notes.app.Scribler.Service.Implements;

import com.notes.app.Scribler.DTO.AddTenantDto;
import com.notes.app.Scribler.DTO.TenantDto;
import com.notes.app.Scribler.Entity.Tenant;
import com.notes.app.Scribler.Repository.TenantRepository;
import com.notes.app.Scribler.Service.TenantService;
import com.notes.app.Scribler.enums.SubType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantServiceImp implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantServiceImp(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public TenantDto addTenant(AddTenantDto addTenantDto) {
        if (tenantRepository.findBySlug(addTenantDto.getSlug()) != null) {
            throw new IllegalArgumentException("Tenant with slug '" + addTenantDto.getSlug() + "' already exists");
        }
        Tenant tenant = new Tenant();
        tenant.setName(addTenantDto.getName());
        tenant.setSlug(addTenantDto.getSlug());
        tenant.setSubType(SubType.FREE);
        Tenant addedTenant =  tenantRepository.save(tenant);
        return new TenantDto(addedTenant);
    }

    @Override
    public TenantDto upgradeTenant(String slug) {
        Tenant tenant = tenantRepository.findBySlug(slug);
        if (tenant == null) {
            throw new IllegalArgumentException("Tenant not found with slug: " + slug);
        }
        tenant.setSubType(SubType.PRO);
         Tenant updatesSubType  = tenantRepository.save(tenant);
         return new TenantDto(updatesSubType);
    }

    @Override
    public List<TenantDto> getAllRenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        List<TenantDto> tenantDtos = new ArrayList<>();
        for(Tenant tenant : tenants){
            tenantDtos.add(new TenantDto(tenant));
        }
        return tenantDtos;
    }

    @Override
    public TenantDto getTenantBySlug(String slug) {
        Tenant tenant = tenantRepository.findBySlug(slug);
        if (tenant == null) {
            throw new IllegalArgumentException("Tenant not found with slug: " + slug);
        }
        TenantDto tenantDto = new TenantDto(tenant);
        return tenantDto;
    }
}
