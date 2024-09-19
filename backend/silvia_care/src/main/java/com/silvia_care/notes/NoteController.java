package com.silvia_care.notes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> findAll(){
        List<Note> notes = noteService.findAll();

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id){
        Note note = noteService.findById(id);

        return ResponseEntity.ok(note);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<Note>> findByTitle(@RequestParam("title") String title){
        List<Note> notes = noteService.findByTitlePattern(title);

        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<Note> saveNote(@RequestBody Note note, UriComponentsBuilder ucb){
        Note newNote = noteService.save(note);

        URI savedNoteLocation = ucb
                .path("/notes/{id}")
                .buildAndExpand(newNote.getId())
                .toUri();

        return ResponseEntity.created(savedNoteLocation).build();
    }

}
