package com.lds.usermanagement.entity;

import com.devertelo.springswaggercodegen3.model.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresses = new ArrayList<>();

    public void addAddress(AddressEntity address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(AddressEntity address) {
        addresses.remove(address);
        address.setUser(null);
    }
}
