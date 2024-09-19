package com.silvia_care.notes;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteRepositoryTests {

    @Autowired
    private NoteRepository repo;

    @Test
    void shouldReturnAllNotes(){
        final int TOTAL_NOTES = 6;

        List<Note> notes = repo.findAll();

        assertThat(notes.size()).isEqualTo(TOTAL_NOTES);
    }

    @Test
    void shouldReturnNotesByUserName(){
        final String USER_NAME = "Alejandra";
        final int EXPECTED_SIZE = 3;

        List<Note> notes = repo.findByCaregiver_Name(USER_NAME);

        assertThat(notes.size()).isEqualTo(EXPECTED_SIZE);

        notes.stream().forEach(note -> {
            assertThat(note.getCaregiver().getName()).isEqualTo(USER_NAME);
        });
    }

    @Test
    void shouldReturnNotesByUserId(){
        final Long USER_ID = 1L;
        List<Note> notes = repo.findByCaregiver_Id(USER_ID);

        notes.forEach(note -> {
            assertThat(note.getCaregiver().getId()).isEqualTo(USER_ID);
        });
    }

    @Test
    void shouldReturnOnlyActiveNotes(){
        final int TOTAL_NOTES = 6;
        List<Note> notes = repo.findByActiveTrue();

        assertThat(notes.size()).isEqualTo(TOTAL_NOTES);

    }

    @Test
    void shouldReturnNotesByTitlePattern(){
        final String TITLE_PATTERN = "Recorda";
        final int EXPECTED_SIZE = 2;

        List<Note> notes = repo.findByTitleContains(TITLE_PATTERN);

        assertThat(notes.size()).isEqualTo(EXPECTED_SIZE);


        notes.forEach(note -> {
            assertThat(note.getTitle().contains(TITLE_PATTERN)).isTrue();
        });
    }

    @Test
    void shouldReturnNotesByDetailPattern(){
        final String DETAIL_PATTERN = "ejemplo";

        List<Note> notes = repo.findByDetailContains(DETAIL_PATTERN);

        notes.forEach(note -> {
            assertThat(note.getDetail().contains(DETAIL_PATTERN)).isTrue();
        });
    }
}
