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
                        .withFirstName(removeQuotes(person[0]))
                        .withLastName(removeQuotes(person[1]))
                        .withParty(removeQuotes(person[3]))
                        .withIntressentId(removeQuotes(person[4]))
                        .withGender(removeQuotes(person[5]))
                        .withYearOfBirth(removeQuotes(person[6]))
                        .withConstituent(removeQuotes(person[7]))
                        .withStatus(removeQuotes(person[8]))
                        .withWebAddress(removeQuotes(person[9]))
                        .withEmail(removeQuotes(person[10]))
                        .withPhone(removeQuotes(person[11]))
                        .withTitle(removeQuotes(person[12]))
                        .withOrgan(removeQuotes(person[13]))
                        .withRole(removeQuotes(person[14]))
                        .withRoleStatus(removeQuotes(person[15]))
                        .withAssignmentFrom(removeQuotes(person[16]))
                        .withAssignmentTo(removeQuotes(person[17]))
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
                        .withRm(removeQuotes(votingCsv[0]))
                        .withLabel(removeQuotes(votingCsv[1]))
                        .withVotingId(removeQuotes(votingCsv[2]))
                        .withPoint(removeQuotes(votingCsv[3]))
                        .withName(removeQuotes(votingCsv[4]))
                        .withIntressentId(removeQuotes(votingCsv[5]))
                        .withParty(removeQuotes(votingCsv[6]))
                        .withConstituent(removeQuotes(votingCsv[7]))
                        .withVote(removeQuotes(votingCsv[8]))
                        .withRegarding(removeQuotes(votingCsv[9]))
                        .withBenchNumber(removeQuotes(votingCsv[10]))
                        .withGender(removeQuotes(votingCsv[11]))
                        .withYearOfBirth(removeQuotes(votingCsv[12]))
                        .withDate(removeQuotes(votingCsv[13]))
                        .build();
                votingRepository.save(voting);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String removeQuotes(String s) {
       return s.substring(1, s.length() - 1);
    }
}
