package com.api.fleche.model.dtos;

import com.api.fleche.enums.Status;
import com.api.fleche.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "E-mail is invalid")
    private String email;

    @Size(min = 8, message = "Phone number must contain at least 8 characters")
    private String phone;

    @NotNull
    @Size(max = 2, message = "The area code must contain only two digits")
    private String ddd;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 150)
    private String password;

    private UserRole role;

    private Status status;

    @PrePersist
    public void prePersist() {
        this.status = Status.ACTIVE;
        this.role = UserRole.USER;
    }

}
