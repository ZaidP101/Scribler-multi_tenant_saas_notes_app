package com.notes.app.Scribler.Repository;

import com.notes.app.Scribler.Entity.Tenant;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository <Tenant, Long>{
}
