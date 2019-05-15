package com.github.jozephineg.resource;

import com.github.jozephineg.controller.resource.CandidateResource;
import com.github.jozephineg.controller.service.CandidateSearchLogic;
import com.github.jozephineg.model.data.Candidate;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CandidateResourceTest {
    private Candidate olle = new Candidate.Builder()
            .withIntressentId("testIntressentId")
            .withFirstName("Olle")
            .withLastName("Boll")
            .withYearOfBirth("1989")
            .withGender("Woman")
            .withConstituent("Dalarnas Län")
            .withOrgan("testOrgan")
            .withParty("KD")
            .withRole("Ledamot")
            .withTitle("testTitle")
            .build();

    @Test
    public void successfulSearchByConstituency() {
        CandidateSearchLogic candidateSearchLogic = mock(CandidateSearchLogic.class);
        when(candidateSearchLogic.findByConstituency("Dalarnas Län")).thenReturn(List.of(olle));

        CandidateResource candidateResource = new CandidateResource(candidateSearchLogic);

        List<Candidate> response = candidateResource.searchForCandidate("Dalarnas Län", "", "");
        assertEquals(1, response.size());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Dalarnas Län", response.get(0).getConstituent());
        assertEquals("testOrgan", response.get(0).getOrgan());
        assertEquals("KD", response.get(0).getParty());
        assertEquals("Ledamot", response.get(0).getRole());
        assertEquals("testTitle", response.get(0).getTitle());
    }

    @Test
    public void successfulSearchByFirstAndLastName() {
        CandidateSearchLogic candidateSearchLogic = mock(CandidateSearchLogic.class);
        when(candidateSearchLogic.findByFirstNameAndLastName("Olle", "Boll")).thenReturn(List.of(olle));

        CandidateResource candidateResource = new CandidateResource(candidateSearchLogic);

        List<Candidate> response = candidateResource.searchForCandidate("", "Olle", "Boll");
        assertEquals(1, response.size());
        assertEquals("Olle", response.get(0).getFirstName());
        assertEquals("Boll", response.get(0).getLastName());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Dalarnas Län", response.get(0).getConstituent());
        assertEquals("testOrgan", response.get(0).getOrgan());
        assertEquals("KD", response.get(0).getParty());
        assertEquals("Ledamot", response.get(0).getRole());
        assertEquals("testTitle", response.get(0).getTitle());
    }

    @Test
    public void failedSearchByConstituent() {
        CandidateSearchLogic candidateSearchLogic = mock(CandidateSearchLogic.class);
        when(candidateSearchLogic.findByConstituency("Kronobergs län")).thenReturn(List.of(new Candidate.Builder().build()));

        CandidateResource candidateResource = new CandidateResource(candidateSearchLogic);

        List<Candidate> response = candidateResource.searchForCandidate("Dalarnas Län", "", "");
        assertEquals(0, response.size());
    }

    @Test()
    public void failedWhenPassingNull() {
        CandidateSearchLogic candidateSearchLogic = mock(CandidateSearchLogic.class);
        when(candidateSearchLogic.findByConstituency("Stockholms län")).thenReturn(List.of(new Candidate.Builder().build()));

        CandidateResource candidateResource = new CandidateResource(candidateSearchLogic);

        assertThrows(BadRequestException.class, () -> {
            candidateResource.searchForCandidate("", "", "");
        });
    }

    @Test()
    public void failedWhenPassingOnlyFirstName() {
        CandidateSearchLogic candidateSearchLogic = mock(CandidateSearchLogic.class);
        when(candidateSearchLogic.findByFirstNameAndLastName("olle", "boll")).thenReturn(List.of(new Candidate.Builder().build()));

        CandidateResource candidateResource = new CandidateResource(candidateSearchLogic);

        assertThrows(BadRequestException.class, () -> {
            candidateResource.searchForCandidate("", "Olle", "");
        });
    }

    @Test()
    public void failedWhenPassingOnlyLastName() {
        CandidateSearchLogic candidateSearchLogic = mock(CandidateSearchLogic.class);
        when(candidateSearchLogic.findByFirstNameAndLastName("olle", "boll")).thenReturn(List.of(new Candidate.Builder().build()));

        CandidateResource candidateResource = new CandidateResource(candidateSearchLogic);

        assertThrows(BadRequestException.class, () -> {
            candidateResource.searchForCandidate("", "", "Boll");
        });
    }
}
