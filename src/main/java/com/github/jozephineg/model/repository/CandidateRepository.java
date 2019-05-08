package com.github.jozephineg.model.repository;

import com.github.jozephineg.model.data.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends ElasticsearchRepository<Candidate, String> {
    Page<Candidate> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
    Page<Candidate> findByConstituent(String name, Pageable pageable);
}
