package com.github.jozephineg.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jozephineg.model.data.Candidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class CandidateSearchIT {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    ObjectMapper objectMapper;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testSearchingForExistingCandidateUsingConstituency() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/candidates?constituency=\"Kronobergs län\""), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Candidate> candidates = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Candidate.class));
        assertNotEquals(0, candidates.size());
        candidates.forEach(
                candidate -> assertEquals("Kronobergs län", candidate.getConstituent())
        );
    }

    @Test
    public void testSearchingForExistingCandidateUsingFakeConstituency() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/candidates?constituency=\"Fake fake\""), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Candidate> candidates = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Candidate.class));
        assertEquals(0, candidates.size());
    }

    @Test
    public void testSearchingForExistingCandidateUsingFirstAndLastName() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/candidates?firstName=Johan&lastName=Hultberg"), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Candidate> candidates = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Candidate.class));
        assertNotEquals(0, candidates.size());
        candidates.forEach(
                candidate -> {
                    assertEquals("Johan", candidate.getFirstName());
                    assertEquals("Hultberg", candidate.getLastName());
                }
        );
    }

    @Test
    public void testSearchingForExistingCandidateUsingFakeFirstAndLastName() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/candidates?firstName=Nick&lastName=Fury"), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Candidate> candidates = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Candidate.class));
        assertEquals(0, candidates.size());
    }

    @Test
    public void testSearchingWithOnlyFirstName() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/candidates?firstName=Nick"), HttpMethod.GET, entity, String.class);

        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void testSearchingWithOnlyLastName() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/candidates?lastName=Fury"), HttpMethod.GET, entity, String.class);

        assertEquals(400, response.getStatusCode().value());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
