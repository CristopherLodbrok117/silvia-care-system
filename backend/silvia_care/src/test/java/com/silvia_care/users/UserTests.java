package com.silvia_care.users;


import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {
    private final String LOCAL_URI = "http://localhost:8080/api/v1/users";

    private RestTemplate restTemplate = new RestTemplate();


    @Test
    void findUserByName(){

        ResponseEntity<String> response = restTemplate.getForEntity(LOCAL_URI + "?name=Alejandra", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext dc = JsonPath.parse(response.getBody());

        String name = dc.read("$.name");
        assertThat(name).isEqualTo("Alejandra");

    }

    @Test
    void findUserById() {
        ResponseEntity<String> response = restTemplate.getForEntity(LOCAL_URI + "/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
