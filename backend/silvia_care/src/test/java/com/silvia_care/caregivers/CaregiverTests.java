package com.silvia_care.caregivers;


import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaregiverTests {
    private final String LOCAL_URI = "http://localhost:8080/api/caregivers";

    private RestTemplate restTemplate = new RestTemplate();


    @Test
    void findCaregiverByName(){

        ResponseEntity<String> response = restTemplate.getForEntity(LOCAL_URI + "?name=Alejandra", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext dc = JsonPath.parse(response.getBody());

        String name = dc.read("$.name");
        assertThat(name).isEqualTo("Alejandra");

    }

    @Test
    void findCaregiverById() {
        ResponseEntity<String> response = restTemplate.getForEntity(LOCAL_URI + "/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
