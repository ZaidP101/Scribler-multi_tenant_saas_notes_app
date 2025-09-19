package com.notes.app.Scribler.DTO;

import lombok.Data;

@Data
public class AddTenantDto {
    private String name;
    private String slug;
    public AddTenantDto(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

}
