package com.silvia_care.notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service()
@Qualifier("noteService")
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll(){
        List<Note> notes = noteRepository.findAll();

        if(notes.isEmpty()){
            throw new NoteException("Couldn't find any notes");
        }

        return notes;
    }

    public Note findById(Long id){
        Optional<Note> foundNote = noteRepository.findById(id);

        return foundNote.orElseThrow(() -> new NoteException("No existe ninguna nota con id: " + id));
    }

    public List<Note> findByTitlePattern(String title){
        List<Note> notes = noteRepository.findByTitleContains(title);

        if(notes.isEmpty()){
            throw new NoteException("No existen notas con el titulo: " + title);
        }

        return notes;
    }

    public List<Note> findActiveNotes(){
        List<Note> activeNotes = noteRepository.findByActiveTrue();

        if(activeNotes.isEmpty()){
            throw new NoteException("No hay notas activas");
        }

        return activeNotes;
    }

    public Note save(Note note){
        Optional<Note> optionalNote = Optional.ofNullable(noteRepository.save(note));

        return optionalNote.orElseThrow(() ->  new NoteException("No se pudo crear la nota: " + note.getTitle()));

    }
}
