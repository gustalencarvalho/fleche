package com.api.fleche.model;

import com.api.fleche.enums.StatusUserLocation;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "USER_LOCATION_SESSION", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")
})
public class UserLocationSession implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private LocalDateTime dateActive;

    @Column(nullable = false)
    private LocalDateTime dateExpires;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusUserLocation statusUserLocation;

}


