package com.notes.app.Scribler.Controller;

import com.notes.app.Scribler.DTO.AddNoteDto;
import com.notes.app.Scribler.DTO.NoteDto;
import com.notes.app.Scribler.Entity.Note;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Service.NoteService;
import com.notes.app.Scribler.Service.Security.AuthService;
import jakarta.validation.Valid;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;
    private final AuthService authService;

    public NoteController(NoteService noteService, AuthService authService) {
        this.noteService = noteService;
        this.authService = authService;
    }

    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    @PostMapping("/create")
    public NoteDto createNote(@RequestBody @Valid AddNoteDto addNoteDto){
        return noteService.createNote(addNoteDto);
    }

    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    @GetMapping("/allNotes")
    public List<NoteDto> getAllNotes(){
        Long tenantId = AuthService.getCurrentTenantId();
        return noteService.getAllNotes(tenantId);
    }

    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    @PatchMapping("/update/{noteId}")
    public NoteDto updateNote(@PathVariable Long noteId, @RequestBody Map<String, Object> update){
        return noteService.updateNote(noteId, update);
    }

    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    @DeleteMapping("/delete/{noteId}")
    public void deleteNote(@PathVariable Long noteId){
        noteService.deleteNote(noteId);
    }

    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    @GetMapping("/user/{userId}")
    public List<NoteDto> getNotesByUser(@PathVariable Long userId){
        return noteService.getNotesByUser(userId);
    }
}
