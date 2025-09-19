package com.notes.app.Scribler.Service.Implements;

import com.notes.app.Scribler.DTO.AddNoteDto;
import com.notes.app.Scribler.DTO.NoteDto;
import com.notes.app.Scribler.Entity.Note;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Repository.NoteRepository;
import com.notes.app.Scribler.Repository.UserRepository;
import com.notes.app.Scribler.Service.NoteService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImp implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImp(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));
    }

    @Override
    public NoteDto createNote(AddNoteDto addNoteDto) {
        User currentUser = getCurrentUser();

        Note note = new Note();
        note.setTitle(addNoteDto.getTitle());
        note.setContent(addNoteDto.getContent());
        note.setCreatedBy(currentUser);
        note.setTenant(currentUser.getTenant());
        Note addNote = noteRepository.save(note);
        return new NoteDto(addNote);
    }

    @Override
    public List<NoteDto> getAllNotes(Long tenantId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getTenant().getId().equals(tenantId)) {
            throw new IllegalArgumentException("You cannot access another tenant's notes");
        }
        List<Note> notes =  noteRepository.findAllByTenantId(tenantId);
        List<NoteDto> noteDtos = new ArrayList<>();
        for(Note note : notes){
            noteDtos.add(new NoteDto(note));
        }
        return noteDtos;
    }

    @Override
    public void deleteNote(Long noteId) {
        User currentUser = getCurrentUser();

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("No note exists with ID: " + noteId));
        if (!note.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("You can only delete your own notes");
        }
        noteRepository.delete(note);
    }

    @Override
    public List<NoteDto> getNotesByUser(Long userId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getTenant().getId().equals(userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
                .getTenant().getId())) {
            throw new IllegalArgumentException("You cannot access another tenant's notes");
        }
        List<Note> notes = noteRepository.findAllByCreatedBy_Id(userId);
        List<NoteDto> noteDtos  = new ArrayList<>();
        for(Note note : notes){
            noteDtos.add(new NoteDto(note));
        }
        return  noteDtos;
    }

    @Override
    public NoteDto updateNote(Long noteId, Map<String, Object> update) {
        User currentUser = getCurrentUser();
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new IllegalArgumentException("The Exist No Note With ID : "+noteId));
        if (!note.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("You can only update your own notes");
        }
        update.forEach((field, value) ->{
            if (value == null){
                throw new IllegalArgumentException("Field" + field + "cannot be null");
            }
            switch (field){
                case "title": note.setTitle(value.toString());
                break;
                case "content" : note.setContent(value.toString());
                break;
                default: throw new IllegalArgumentException("Field '" + field + "' is not supported");
            }
        });
        note.setUpdatedAt(Instant.now());
        Note updatedNote = noteRepository.save(note);
        return new NoteDto(updatedNote);
    }
}
