package com.notes.app.Scribler.Service.Implements;

import com.notes.app.Scribler.DTO.AddNoteDto;
import com.notes.app.Scribler.DTO.NoteDto;
import com.notes.app.Scribler.Entity.Note;
import com.notes.app.Scribler.Repository.NoteRepository;
import com.notes.app.Scribler.Service.NoteService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImp implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImp(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public NoteDto createNote(AddNoteDto addNoteDto) {
        Note note = new Note();
        note.setTitle(addNoteDto.getTitle());
        note.setContent(addNoteDto.getContent());
        note.setCreatedBy(addNoteDto.getCreatedBy());
        Note addNote =  noteRepository.save(note);
        return new NoteDto(addNote);
    }

    @Override
    public List<NoteDto> getAllNotes(Long tenantId) {
        List<Note> notes =  noteRepository.findAllByTenantId(tenantId);
        List<NoteDto> noteDtos = new ArrayList<>();
        for(Note note : notes){
            noteDtos.add(new NoteDto(note));
        }
        return noteDtos;
    }

    @Override
    public void deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("No note exists with ID: " + noteId));
        noteRepository.delete(note);
    }

    @Override
    public List<NoteDto> getNotesByUser(Long userId) {
        List<Note> notes = noteRepository.findAllByCreatedBy_Id(userId);
        List<NoteDto> noteDtos  = new ArrayList<>();
        for(Note note : notes){
            noteDtos.add(new NoteDto(note));
        }
        return  noteDtos;
    }

    @Override
    public NoteDto updateNote(Long noteId, Map<String, Object> update) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new IllegalArgumentException("The Exist No Note With ID : "+noteId));
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
