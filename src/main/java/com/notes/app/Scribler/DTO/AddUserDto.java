package com.notes.app.Scribler.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.notes.app.Scribler.Entity.Tenant;
import com.notes.app.Scribler.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddUserDto {
    private String name;
    private String email;
    private String password;
    private Role role;
    private Tenant tenant;

    public AddUserDto(String name, String email, String password, Role role, Tenant tenant) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tenant = tenant;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Tenant getTenant() {
        return tenant;
    }
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
