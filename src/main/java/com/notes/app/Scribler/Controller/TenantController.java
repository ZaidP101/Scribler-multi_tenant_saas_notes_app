package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.AddTenantDto;
import com.notes.app.Scribler.DTO.TenantDto;
import com.notes.app.Scribler.Service.TenantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }
    @PostMapping("/createTenant")
    public TenantDto createTenant(@RequestBody @Valid AddTenantDto addTenantDto){
        TenantDto newTenant = tenantService.addTenant(addTenantDto);
        return newTenant;
    }

    @PostMapping("/{slug}/upgrade")
    public TenantDto upgradeTenant(@PathVariable String slug) {
        return tenantService.upgradeTenant(slug);
    }

    @GetMapping("/allTenants")
    public List<TenantDto> getAllTenants(){
        return tenantService.getAllRenants();
    }

    @GetMapping("/{slug}")
    public TenantDto getTenantBySlug(@PathVariable String slug) {
        return tenantService.getTenantBySlug(slug);
    }
}
