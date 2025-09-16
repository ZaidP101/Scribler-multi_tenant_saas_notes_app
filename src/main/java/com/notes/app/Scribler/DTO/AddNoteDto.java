package com.notes.app.Scribler.DTO;

import lombok.Data;

@Data
public class AddNoteDto {
    private String title;
    private String content;
    private Long createdBy;

    public AddNoteDto(String title, String content, Long createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public Long getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
