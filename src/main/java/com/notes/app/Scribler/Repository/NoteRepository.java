package com.notes.app.Scribler.Repository;

import com.notes.app.Scribler.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository <Note, Long>{
}
