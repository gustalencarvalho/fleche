package com.api.fleche.model;

import com.api.fleche.enums.StatusBlockade;
import com.api.fleche.model.exception.UserNotBlockHimselfException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "USER_BLOCKS")
public class UserBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blocked_user_id", nullable = false)
    private User blockedUser;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(length = 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    StatusBlockade status;

    @PrePersist
    @PreUpdate
    private void validateBlock() {
        if (user != null && blockedUser != null && user.getId().equals(blockedUser.getId())) {
            throw new UserNotBlockHimselfException("A user cannot block himself.");
        }
    }
}
