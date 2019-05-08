package com.github.jozephineg.controller.service;

import com.github.jozephineg.model.data.Voting;
import com.github.jozephineg.model.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotingSearchLogic {

    private final VotingRepository votingRepository;

    @Autowired
    public VotingSearchLogic(VotingRepository votingRepository) {
        this.votingRepository = votingRepository;
    }

    public List<Voting> findByConstituency(String constituent) {
        return votingRepository.findByConstituent(constituent, Pageable.unpaged()).stream().collect(Collectors.toList());

    }

    public List<Voting> findByVotingId(String votingId) {
        return votingRepository.findByVotingId(votingId, Pageable.unpaged()).stream().collect(Collectors.toList());

    }
}
