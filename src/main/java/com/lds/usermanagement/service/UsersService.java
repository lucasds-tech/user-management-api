package com.lds.usermanagement.service;

import com.devertelo.springswaggercodegen3.model.UserRequest;
import com.devertelo.springswaggercodegen3.model.UserResponse;
import com.lds.usermanagement.entity.UsersEntity;
import com.lds.usermanagement.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        UsersEntity usersEntity = toEntity(userRequest);
        usersEntity.setCreatedAt(OffsetDateTime.now());
        usersRepository.save(usersEntity);
        return toResponse(usersEntity);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return usersRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse getById(UUID id) {
        return usersRepository.findById(id)
                .map(this::toResponse)
                .orElse(null);
    }

    @Transactional
    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        return usersRepository.findById(id).map(existing -> {
            existing.setUsername(userRequest.getUsername());
            existing.setName(userRequest.getName());
            existing.setLastName(userRequest.getLastName());
            existing.setEmail(userRequest.getEmail());
            existing.setPassword(userRequest.getPassword());
            existing.setStatus(userRequest.getStatus());

            usersRepository.save(existing);
            return toResponse(existing);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteUser(UUID id) {
        return usersRepository.findById(id).map(u -> {
            usersRepository.delete(u);
            return true;
        }).orElse(false);
    }

    private UsersEntity toEntity(UserRequest userRequest) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(userRequest.getUsername());
        usersEntity.setName(userRequest.getName());
        usersEntity.setLastName(userRequest.getLastName());
        usersEntity.setEmail(userRequest.getEmail());
        usersEntity.setPassword(userRequest.getPassword());
        usersEntity.setStatus(userRequest.getStatus());

        return usersEntity;
    }

    private UserResponse toResponse(UsersEntity usersEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(usersEntity.getId().toString());
        userResponse.setUsername(usersEntity.getUsername());
        userResponse.setName(usersEntity.getName());
        userResponse.setLastName(usersEntity.getLastName());
        userResponse.setEmail(usersEntity.getEmail());
        userResponse.setPassword(usersEntity.getPassword());

        if (usersEntity.getStatus() != null) {
            userResponse.setStatus(usersEntity.getStatus());
        }

        userResponse.setCreatedAt(usersEntity.getCreatedAt());
        return userResponse;
    }
}
