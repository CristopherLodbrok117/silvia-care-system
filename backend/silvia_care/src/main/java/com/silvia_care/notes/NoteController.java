package com.silvia_care.notes;

import com.silvia_care.caregivers.CaregiverDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    NoteService noteService;
    CaregiverDetailService caregiverDetailService;

    public NoteController(NoteService noteService, CaregiverDetailService caregiverDetailService){
        this.noteService = noteService;
        this.caregiverDetailService = caregiverDetailService;
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
