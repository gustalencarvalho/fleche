package com.api.fleche.model;

import com.api.fleche.enums.StatusLike;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LIKES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id_orige", "user_id_destiny"})
})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID_ORIGIN", nullable = false)
    private User userOrigin;

    @ManyToOne
    @JoinColumn(name = "USER_ID_DESTINY", nullable = false)
    private User userDestiny;

    @Column(name = "STATUS_LIKE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusLike status;

    @Column(name = "DATA_LIKE", nullable = false, updatable = false)
    private LocalDateTime dateLike = LocalDateTime.now();

    public Like(User userOrigin, User userDestiny, StatusLike status) {
        this.userOrigin = userOrigin;
        this.userDestiny = userDestiny;
        this.status = status;
        this.dateLike = LocalDateTime.now();
    }

}