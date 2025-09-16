package com.notes.app.Scribler.DTO;

import com.notes.app.Scribler.Entity.Note;
import com.notes.app.Scribler.Entity.Tenant;
import lombok.Data;

import java.time.Instant;

@Data
public class NoteDto {
    private Long id;
    private  String title;
    private  String content;
    private Instant createdAt;
    private Instant updatedAt;
    private Long createdBy;
    private Tenant tenant;

    public NoteDto(Long id, String title, String content, Instant createdAt, Instant updatedAt, Long createdBy, Tenant tenant) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.tenant = tenant;
    }

    public NoteDto(Note addNote) {
        this.id = addNote.getId();
        this.title = addNote.getTitle();
        this.content = addNote.getContent();
        this.createdAt = addNote.getCreatedAt();
        this.updatedAt = addNote.getUpdatedAt();
        this.createdBy = addNote.getCreatedBy();
        this.tenant = addNote.getTenant();
    }
}

