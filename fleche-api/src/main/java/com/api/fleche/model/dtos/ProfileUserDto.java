package com.api.fleche.model.dtos;

import com.api.fleche.enums.Gender;
import com.api.fleche.enums.Preferences;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileUserDto {

    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, length = 150)
    private String bio;

    @Lob
    private byte[] picture;

    private String filme;

    private String lazer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Preferences preferences;
}
