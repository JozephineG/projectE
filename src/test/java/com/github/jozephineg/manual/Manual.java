package com.github.jozephineg.manual;

import com.github.jozephineg.config.Config;
import com.github.jozephineg.model.data.Candidate;
import com.github.jozephineg.model.data.Voting;
import com.github.jozephineg.model.repository.CandidateRepository;
import com.github.jozephineg.model.repository.VotingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Manual {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Test
    public void loadCandidateData() {
        String csvFile = "/Users/jozephinegronqvist/Downloads/person.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] person = line.split(cvsSplitBy);

                Candidate candidate = new Candidate.Builder()
                        .withFirstName(person[0])
                        .withLastName(person[1])
                        .withParty(person[3])
                        .withIntressentId(person[4])
                        .withGender(person[5])
                        .withYearOfBirth(person[6])
                        .withConstituent(person[7])
                        .withStatus(person[8])
                        .withWebAddress(person[9])
                        .withEmail(person[10])
                        .withPhone(person[11])
                        .withTitle(person[12])
                        .withOrgan(person[13])
                        .withRole(person[14])
                        .withRoleStatus(person[15])
                        .withAssignmentFrom(person[16])
                        .withAssignmentTo(person[17])
                        .build();
                candidateRepository.save(candidate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void loadVotingData() {
        String csvFile = "/Users/jozephinegronqvist/Downloads/voteringar-short.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] votingCsv = line.split(cvsSplitBy);

                Voting voting = new Voting.Builder()
                        .withRm(votingCsv[0])
                        .withLabel(votingCsv[1])
                        .withPoint(votingCsv[2])
                        .withVotingId(votingCsv[3])
                        .withName(votingCsv[4])
                        .withIntressentId(votingCsv[5])
                        .withParty(votingCsv[6])
                        .withConstituent(votingCsv[7])
                        .withVote(votingCsv[8])
                        .withRegarding(votingCsv[9])
                        .withBenchNumber(votingCsv[10])
                        .withGender(votingCsv[11])
                        .withYearOfBirth(votingCsv[12])
                        .withDate(votingCsv[13])
                        .build();
                votingRepository.save(voting);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
