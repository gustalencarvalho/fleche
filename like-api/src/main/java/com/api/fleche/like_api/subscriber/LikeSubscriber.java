package com.api.fleche.like_api.subscriber;

import com.api.fleche.like_api.model.Like;
import com.api.fleche.like_api.service.LikeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeSubscriber {

    private final ObjectMapper mapper;
    private final LikeService service;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${fleche.config.kafka.topics.fleche-like}"
    )
    public void sendLike(String payload) {
        try {
            log.info("Recebendo LIKE: {} ", payload);
            var saveLike = mapper.readValue(payload, Like.class);
            var like = new Like();
            like.setStatus(saveLike.getStatus());
            like.setUserOriginId(saveLike.getUserOriginId());
            like.setUserDestinyId(saveLike.getUserDestinyId());
            service.like(like);

            log.info("Like realizado com sucesso!");
        } catch (Exception e) {
            log.error("Erro ao dar like {} ", e.getMessage());
        }
    }
}
