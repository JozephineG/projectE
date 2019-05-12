package com.github.jozephineg.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jozephineg.model.data.Candidate;
import com.github.jozephineg.model.data.Voting;
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
public class VotingSearchIT {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    ObjectMapper objectMapper;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testSearchingForVotingUsingConstituency() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/votings?constituency=\"Östergötlands län\""), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Voting> votings = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Voting.class));
        assertNotEquals(0, votings.size());
        votings.forEach(
                voting -> assertEquals("Östergötlands län", voting.getConstituent())
        );
    }

    @Test
    public void testSearchingForVotingUsingFakeConstituency() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/votings?constituency=\"fake län\""), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Voting> votings = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Voting.class));
        assertEquals(0, votings.size());
    }

    @Test
    public void testSearchingForVotingUsingVotingId() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/votings?votingId=0069C0F4-729A-4970-96B0-56C306EC698C"), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Voting> votings = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Voting.class));
        assertNotEquals(0, votings.size());
        votings.forEach(
                voting -> assertEquals("0069C0F4-729A-4970-96B0-56C306EC698C", voting.getVotingId())
        );
    }

    @Test
    public void testSearchingForVotingUsingFakeVotingId() throws IOException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/votings?votingId=fakifake"), HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCode().value());
        List<Voting> votings = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Voting.class));
        assertEquals(0, votings.size());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
