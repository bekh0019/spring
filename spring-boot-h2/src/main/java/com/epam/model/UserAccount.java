package com.epam.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private PhoneCompany company;

    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Phone phone;
}
