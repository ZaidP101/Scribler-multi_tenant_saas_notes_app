package com.notes.app.Scribler.Service;

import com.notes.app.Scribler.DTO.AddNoteDto;
import com.notes.app.Scribler.DTO.NoteDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface    NoteService {
    NoteDto createNote(@Valid AddNoteDto addNoteDto);

    List<NoteDto> getAllNotes(Long tenantId);

    void deleteNote(Long noteId);

    List<NoteDto> getNotesByUser(Long userId);

    NoteDto updateNote(Long noteId, Map<String, Object> update);
}
