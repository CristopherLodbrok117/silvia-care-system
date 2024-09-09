package com.silvia_care.notes;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.silvia_care.users.User;
import com.silvia_care.users.UserRepository;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteControllerTests {

    static final String LOCAL_URI = "http://localhost:8080/notes";

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserRepository userRepository;

    @Test
    @DirtiesContext
    void shouldCreateANewNote() {

        User alejandra = userRepository.findByName("alejandra");

        String noteTitle = "Nuevo titulo";

        // Creation assertions
        Note newNote = new Note(noteTitle
                , "Ejemplo de nota"
                , LocalDate.of(2024, 12, 9)
                , alejandra);

        ResponseEntity<Void> response = restTemplate.postForEntity(LOCAL_URI, newNote, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // New URI assertions
        URI newNoteUri = response.getHeaders().getLocation();
        ResponseEntity<String> getResponse  = restTemplate.getForEntity(newNoteUri, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Additional assertions
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

        String title = documentContext.read("$.title");

        assertThat(title).isNotNull();
        assertThat(title).isEqualTo(noteTitle);

    }

    @Test
    void shouldReturnAllNotes(){

        ResponseEntity<String> response = restTemplate
                .getForEntity(LOCAL_URI, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }


}
