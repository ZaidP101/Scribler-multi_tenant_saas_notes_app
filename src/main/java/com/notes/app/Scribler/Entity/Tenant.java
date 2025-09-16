package com.notes.app.Scribler.Entity;

import com.notes.app.Scribler.enums.SubType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tenant {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String slug;
    @Enumerated(EnumType.STRING)
    private SubType subType = SubType.FREE;

    @OneToMany(mappedBy = "tenant")
    private List<User> users;
    @OneToMany(mappedBy = "tenant")
    private List<Note> notes;

    public SubType getSubType() {
        return subType;
    }
    public void setSubType(SubType subType) {
        this.subType = subType;
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
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<Note> getNotes() {
        return notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
