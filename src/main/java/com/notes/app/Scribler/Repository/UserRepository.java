package com.notes.app.Scribler.Repository;

import com.notes.app.Scribler.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    List<User> findAllBYTenant_Id(Long tenantId);

    Optional<User> findByEmail(String email);
}
