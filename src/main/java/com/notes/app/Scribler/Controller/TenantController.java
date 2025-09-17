package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.AddTenantDto;
import com.notes.app.Scribler.DTO.TenantDto;
import com.notes.app.Scribler.Service.TenantService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createTenant")
    public TenantDto createTenant(@RequestBody @Valid AddTenantDto addTenantDto){
        TenantDto newTenant = tenantService.addTenant(addTenantDto);
        return newTenant;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{slug}/upgrade")
    public TenantDto upgradeTenant(@PathVariable String slug) {
        return tenantService.upgradeTenant(slug);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allTenants")
    public List<TenantDto> getAllTenants(){
        return tenantService.getAllRenants();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{slug}")
    public TenantDto getTenantBySlug(@PathVariable String slug) {
        return tenantService.getTenantBySlug(slug);
    }
}
