package com.epam.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "userCash", nullable = false)
    private BigDecimal userCash;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
