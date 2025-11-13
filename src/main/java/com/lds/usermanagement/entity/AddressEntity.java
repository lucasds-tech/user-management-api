package com.lds.usermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "address")

public class AddressEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String cep;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity users;

}
