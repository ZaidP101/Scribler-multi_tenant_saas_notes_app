package com.notes.app.Scribler.Repository;

import com.notes.app.Scribler.Entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository <Tenant, Long>{
    Tenant findBySlug(String slug);
}
