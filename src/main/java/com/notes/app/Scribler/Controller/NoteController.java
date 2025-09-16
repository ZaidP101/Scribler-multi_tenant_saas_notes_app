package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.AddNoteDto;
import com.notes.app.Scribler.DTO.NoteDto;
import com.notes.app.Scribler.Entity.Note;
import com.notes.app.Scribler.Service.NoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public NoteDto createNote(@RequestBody @Valid AddNoteDto addNoteDto){
        NoteDto noteDto = noteService.createNote(addNoteDto);
        return noteDto;
    }

    @GetMapping("/allNotes/{tenant_id}")
    public List<NoteDto> getAllNotes(@PathVariable Long tenant_id){
        return noteService.getAllNotes(tenant_id);
    }

    @DeleteMapping("/delete/note_id")
    public void deleteNote(@PathVariable Long noteId){
        noteService.deleteNote(noteId);
    }

    @GetMapping("/user/{user_id}")
    public List<NoteDto> getNotesByUser(@PathVariable Long user_id){
        return noteService.getNotesByUser(user_id);
    }

    @PatchMapping("/update/{note_id}")
    public NoteDto updateNote(@PathVariable Long note_id, @RequestBody Map<String, Object> update){
        return noteService.updateNote(note_id, update);
    }
}
