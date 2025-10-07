package com.api.fleche.model;

import com.api.fleche.enums.Status;
import com.api.fleche.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 500)
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String ddd;

    @Size(max = 15)
    private String phone;

    @NotNull
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @Column(nullable = false)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "userId", fetch = FetchType.LAZY, optional = true)
    @JsonIgnore
    private ProfileUser profileUser;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    public User(String name, String email, String ddd, String phone, LocalDate dateOfBirth, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.ddd = ddd;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String name, String email, String ddd, String phone,
                LocalDate dateOfBirth, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.ddd = ddd;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority(("ROLE_USER")));
        else  return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
