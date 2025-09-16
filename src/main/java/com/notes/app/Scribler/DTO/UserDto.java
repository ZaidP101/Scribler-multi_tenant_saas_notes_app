package com.notes.app.Scribler.DTO;

import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long tenant_id;
    private Role role;

    public UserDto(Role role, Long tenant_id, String password, String email, String name, Long id) {
        this.role = role;
        this.tenant_id = tenant_id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public UserDto(User user) {
        this.role = user.getRole();
        this.tenant_id = user.getTenant().getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
        this.id = user.getId();
    }
}
