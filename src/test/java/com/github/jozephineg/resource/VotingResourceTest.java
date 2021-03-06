package com.github.jozephineg.resource;

import com.github.jozephineg.controller.resource.VotingResource;
import com.github.jozephineg.controller.service. VotingSearchLogic;
import com.github.jozephineg.model.data.Voting;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VotingResourceTest {
    private Voting voting = new Voting.Builder()
            .withIntressentId("testIntressentId")
                .withVotingId("votingId")
                .withYearOfBirth("1989")
                .withGender("Woman")
                .withConstituent("Dalarnas Län")
                .withParty("KD")
                .build();

    @Test
    public void successfulSearchByVoting() {
        VotingSearchLogic votingSearchLogic = mock(VotingSearchLogic.class);
        when(votingSearchLogic.findByConstituency("Dalarnas Län")).thenReturn(List.of(voting));

        VotingResource votingResource = new VotingResource(votingSearchLogic);

        List<Voting> response = votingResource.searchForVoting("Dalarnas Län", "");
        assertEquals(1, response.size());
        assertEquals("votingId", response.get(0).getVotingId());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Dalarnas Län", response.get(0).getConstituent());
        assertEquals("KD", response.get(0).getParty());
    }

    @Test
    public void successfulSearchByVotingId() {
        VotingSearchLogic votingSearchLogic = mock( VotingSearchLogic.class);
        when(votingSearchLogic.findByVotingId("votingId")).thenReturn(List.of(voting));

        VotingResource votingResource = new VotingResource(votingSearchLogic);

        List<Voting> response = votingResource.searchForVoting("", "votingId");
        assertEquals(1, response.size());
        assertEquals("votingId", response.get(0).getVotingId());
        assertEquals("testIntressentId", response.get(0).getIntressentId());
        assertEquals("1989", response.get(0).getYearOfBirth());
        assertEquals("Woman", response.get(0).getGender());
        assertEquals("Dalarnas Län", response.get(0).getConstituent());
        assertEquals("KD", response.get(0).getParty());
    }

    @Test
    public void failedSearchByConstituent() {
         VotingSearchLogic votingSearchLogic = mock( VotingSearchLogic.class);
        when(votingSearchLogic.findByConstituency("Kronobergs län")).thenReturn(List.of(new Voting.Builder().build()));

        VotingResource votingResource = new VotingResource(votingSearchLogic);

        List<Voting> response = votingResource.searchForVoting("Dalarnas Län", "");
        assertEquals(0, response.size());
    }

    @Test
    public void failedSearchByVoting() {
        VotingSearchLogic votingSearchLogic = mock( VotingSearchLogic.class);
        when(votingSearchLogic.findByVotingId("exists")).thenReturn(List.of(new Voting.Builder().build()));

        VotingResource votingResource = new VotingResource(votingSearchLogic);

        List<Voting> response = votingResource.searchForVoting("", "doesnotexist");
        assertEquals(0, response.size());
    }

    @Test()
    public void failedWhenPassingNull() {
         VotingSearchLogic votingSearchLogic = mock( VotingSearchLogic.class);
        when(votingSearchLogic.findByConstituency("Stockholms län")).thenReturn(List.of(new Voting.Builder().build()));

        VotingResource votingResource = new VotingResource(votingSearchLogic);

        assertThrows(BadRequestException.class, () -> {
            votingResource.searchForVoting("", "");
        });
    }
}
