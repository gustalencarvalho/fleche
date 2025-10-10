package com.api.fleche.like_api.service;

import com.api.fleche.like_api.model.Match;
import com.api.fleche.like_api.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public void save(Match match) {
        repository.save(match);
    }
}
