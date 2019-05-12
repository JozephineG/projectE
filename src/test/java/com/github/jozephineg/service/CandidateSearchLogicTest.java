package com.github.jozephineg.service;

import com.github.jozephineg.controller.service.CandidateSearchLogic;
import com.github.jozephineg.model.data.Candidate;
import com.github.jozephineg.model.repository.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CandidateSearchLogicTest {

    private Candidate testCandidateSthlm = new Candidate.Builder()
            .withFirstName("Carol")
            .withLastName("Danvers")
            .withIntressentId("testIntressentId")
            .withYearOfBirth("1989")
            .withGender("Woman")
            .withConstituent("Stockholms län")
            .withOrgan("testOrgan")
            .withParty("KD")
            .withRole("Ledamot")
            .withTitle("testTitle")
            .build();

    @Test
    public void successfullyFindByConstituencyTest(){
        CandidateRepository candidateRepository = mock(CandidateRepository.class);
        when(candidateRepository.findByConstituent(eq("Stockholms län"), any())).thenReturn(new PageImpl<>(List.of(testCandidateSthlm)));
        CandidateSearchLogic candidateSearchLogic = new CandidateSearchLogic(candidateRepository);

        List<Candidate> response = candidateSearchLogic.findByConstituency("Stockholms län");
        assertEquals(1, response.size());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Stockholms län", response.get(0).getConstituent());
        assertEquals("testOrgan", response.get(0).getOrgan());
        assertEquals("KD", response.get(0).getParty());
        assertEquals("Ledamot", response.get(0).getRole());
        assertEquals("testTitle", response.get(0).getTitle());
    }

    @Test
    public void unsuccessfullyFindByConstituencyTest() {
        CandidateRepository candidateRepository = mock(CandidateRepository.class);
        when(candidateRepository.findByConstituent(any(), any())).thenReturn(new PageImpl<>(List.of()));
        CandidateSearchLogic candidateSearchLogic = new CandidateSearchLogic(candidateRepository);
        assertEquals(0, candidateSearchLogic.findByConstituency("Stockholms län").size());
    }

    @Test
    public void successfullyFindByNameTest(){
        CandidateRepository candidateRepository = mock(CandidateRepository.class);
        when(candidateRepository.findByFirstNameAndLastName(eq("Carol"), eq("Danvers"), any())).thenReturn(new PageImpl<>(List.of(testCandidateSthlm)));
        CandidateSearchLogic candidateSearchLogic = new CandidateSearchLogic(candidateRepository);

        List<Candidate> response = candidateSearchLogic.findByFirstNameAndLastName("Carol", "Danvers");
        assertEquals(1, response.size());
        assertEquals("Carol", response.get(0).getFirstName());
        assertEquals("Danvers", response.get(0).getLastName());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Stockholms län", response.get(0).getConstituent());
        assertEquals("testOrgan", response.get(0).getOrgan());
        assertEquals("KD", response.get(0).getParty());
        assertEquals("Ledamot", response.get(0).getRole());
        assertEquals("testTitle", response.get(0).getTitle());
    }

    @Test
    public void unsuccessfullyFindByNameTest() {
        CandidateRepository candidateRepository = mock(CandidateRepository.class);
        when(candidateRepository.findByFirstNameAndLastName(any(), any(), any())).thenReturn(new PageImpl<>(List.of()));
        CandidateSearchLogic candidateSearchLogic = new CandidateSearchLogic(candidateRepository);
        assertEquals(0, candidateSearchLogic.findByFirstNameAndLastName("Olle", "Boll").size());
    }
}
