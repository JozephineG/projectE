package com.github.jozephineg.model.repository;

import com.github.jozephineg.model.data.Voting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends ElasticsearchRepository<Voting, String> {
    Page<Voting> findByConstituent(String constituent, Pageable pageable);
    Page<Voting> findByVotingId(String votingId, Pageable pageable);
}
