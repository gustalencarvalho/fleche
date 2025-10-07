package com.api.fleche.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROFILE_USER")
public class ProfileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Lob
    private byte[] picture;

    @Column
    @NotNull(message = "Gender is required")
    private String gender;

    @Column
    @NotNull(message = "Preferences is required")
    private String preferences;

    @Column
    @Size(max = 150)
    @NotNull(message = "Bio is required")
    private String bio;

    @Column
    private String filme;

    @Column
    private String lazer;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User userId;

}
