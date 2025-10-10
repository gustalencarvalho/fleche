package com.api.fleche.like_api.repository;

import com.api.fleche.like_api.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
