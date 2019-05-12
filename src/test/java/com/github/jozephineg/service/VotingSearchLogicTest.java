package com.github.jozephineg.service;

import com.github.jozephineg.controller.service.VotingSearchLogic;
import com.github.jozephineg.model.data.Voting;
import com.github.jozephineg.model.repository.VotingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VotingSearchLogicTest {
    private Voting testVoting = new Voting.Builder()
            .withIntressentId("testIntressentId")
            .withVotingId("votingId")
            .withYearOfBirth("1989")
            .withGender("Woman")
            .withConstituent("Dalarnas Län")
            .withParty("KD")
            .build();

    @Test
    public void successfullyFindByConstituencyTest() {
        VotingRepository votingRepository = mock(VotingRepository.class);
        when(votingRepository.findByConstituent(eq("Dalarnas Län"), any())).thenReturn(new PageImpl<>(List.of(testVoting)));
        VotingSearchLogic votingSearchLogic = new VotingSearchLogic(votingRepository);

        List<Voting> response = votingSearchLogic.findByConstituency("Dalarnas Län");
        assertEquals(1, response.size());
        assertEquals("votingId", response.get(0).getVotingId());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Dalarnas Län", response.get(0).getConstituent());
        assertEquals("KD", response.get(0).getParty());
    }

    @Test
    public void successfullyFindByVotingIdTest() {
        VotingRepository votingRepository = mock(VotingRepository.class);
        when(votingRepository.findByVotingId(eq("votingId"), any())).thenReturn(new PageImpl<>(List.of(testVoting)));
        VotingSearchLogic votingSearchLogic = new VotingSearchLogic(votingRepository);

        List<Voting> response = votingSearchLogic.findByVotingId("votingId");
        assertEquals(1, response.size());
        assertEquals("votingId", response.get(0).getVotingId());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Dalarnas Län", response.get(0).getConstituent());
        assertEquals("KD", response.get(0).getParty());
    }

    @Test
    public void unsuccessfullyFindByVotingIdTest() {
        VotingRepository votingRepository = mock(VotingRepository.class);
        when(votingRepository.findByVotingId(any(), any())).thenReturn(new PageImpl<>(List.of()));
        VotingSearchLogic votingSearchLogic = new VotingSearchLogic(votingRepository);

        assertEquals(0, votingSearchLogic.findByVotingId("votingId").size());
    }

    @Test
    public void unsuccessfullyFindByConstituencyTest() {
        VotingRepository votingRepository = mock(VotingRepository.class);
        when(votingRepository.findByConstituent(any(), any())).thenReturn(new PageImpl<>(List.of()));
        VotingSearchLogic votingSearchLogic = new VotingSearchLogic(votingRepository);

        assertEquals(0, votingSearchLogic.findByConstituency("Stockholms län").size());
    }
}
