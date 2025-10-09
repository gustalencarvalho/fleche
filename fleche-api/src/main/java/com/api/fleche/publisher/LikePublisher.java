package com.api.fleche.publisher;

import com.api.fleche.publisher.representation.LikeRepresentation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    @Value("${fleche.config.kafka.topics.fleche-like}")
    private String topico;

    public void publisherLike(LikeRepresentation like) {
        log.info("Like de {} enviado para {} ", like.userOriginId(), like.userDestinyId());
        try {
            var json = mapper.writeValueAsString(like);
            kafkaTemplate.send(topico, "fleche", json);
        } catch (JsonProcessingException e) {
            log.error("Error in process the json", e);
        } catch (RuntimeException e) {
            log.error("Error technical of publisher in topic like", e);
        }
    }

}
