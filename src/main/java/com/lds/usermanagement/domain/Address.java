package com.lds.usermanagement.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private String cep;
    private String street;
    private String number;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
