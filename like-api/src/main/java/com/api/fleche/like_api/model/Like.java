package com.api.fleche.like_api.model;

import com.api.fleche.like_api.enums.StatusLike;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long id;

    private Long userOriginId;

    private Long userDestinyId;

    @Column(name = "STATUS_LIKE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusLike status;

    @Column(name = "DATA_LIKE", nullable = false, updatable = false)
    private LocalDateTime dateLike = LocalDateTime.now();

    public Like(Long userOriginId, Long userDestinyId, StatusLike status) {
        this.userOriginId = userOriginId;
        this.userDestinyId = userDestinyId;
        this.status = status;
        this.dateLike = LocalDateTime.now();
    }

}