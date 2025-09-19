package com.notes.app.Scribler.DTO;

import lombok.Data;

@Data
public class AddNoteDto {
    private String title;
    private String content;


    public AddNoteDto(String title, String content) {
        this.title = title;
        this.content = content;
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
