package com.notes.app.Scribler.DTO;

import com.notes.app.Scribler.Entity.Note;
import com.notes.app.Scribler.Entity.Tenant;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.enums.SubType;
import lombok.Data;

import java.util.List;

@Data
public class TenantDto {
    private Long id;
    private String name;
    private String slug;
    private SubType subType;
    private List<User> users;
    private List<Note> notes;

    public TenantDto(Long id, String name, String slug, Integer noteLimit, SubType subType, List<User> users, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.subType = subType;
        this.users = users;
        this.notes = notes;
    }


    public TenantDto(Tenant addedTenant) {
        this.id = addedTenant.getId();
        this.name = addedTenant.getName();
        this.slug = addedTenant.getSlug();
        this.subType = addedTenant.getSubType();
        this.users = addedTenant.getUsers();
        this.notes = addedTenant.getNotes();
    }
}
