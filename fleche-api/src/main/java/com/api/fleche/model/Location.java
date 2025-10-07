package com.api.fleche.model;

import com.api.fleche.enums.States;
import com.api.fleche.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LOCATION")
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, columnDefinition = "text[]")
    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<String> coordinate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private States state;

    @Column(nullable = false, unique = true)
    private String qrCode;

    @Column(name = "date_registry", updatable = false)
    @CreationTimestamp
    private LocalDateTime dateRegistry;

    @PrePersist
    public void prePersist() {
        this.dateRegistry = LocalDateTime.now(ZoneId.of("UTC"));
        this.qrCode = UUID.randomUUID().toString();
    }

}