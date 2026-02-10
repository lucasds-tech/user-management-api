package com.lds.usermanagement.controller;

import com.devertelo.springswaggercodegen3.api.UsersApi;
import com.devertelo.springswaggercodegen3.model.UserRequest;
import com.devertelo.springswaggercodegen3.model.UserResponse;
import com.lds.usermanagement.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        var users = usersService.createUser(userRequest);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        boolean deletedUser = usersService.deleteUser(UUID.fromString(id));
        return deletedUser ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        var user = usersService.getById(UUID.fromString(id));
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        var updated = usersService.updateUser(UUID.fromString(id), userRequest);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
