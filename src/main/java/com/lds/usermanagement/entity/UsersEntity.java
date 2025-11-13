package com.lds.usermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue
    private String id;

    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String status;

    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresses = new ArrayList<>();

    public void addAddress(AddressEntity address) {
        addresses.add(address);
        address.setUsers(this);
    }

    public void removeAddress(AddressEntity address) {
        addresses.remove(address);
        address.setUsers(null);
    }
}
