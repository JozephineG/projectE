package com.github.jozephineg.controller.service;

import com.github.jozephineg.model.data.Candidate;
import com.github.jozephineg.model.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateSearchLogic {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateSearchLogic(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> findByConstituency(String constituent) {
        return candidateRepository.findByConstituent(constituent, Pageable.unpaged()).stream().collect(Collectors.toList());
    }

    public List<Candidate> findByFirstNameAndLastName(String firstName, String lastName) {
        return candidateRepository.findByFirstNameAndLastName(firstName, lastName, Pageable.unpaged()).stream().collect(Collectors.toList());
    }
}
