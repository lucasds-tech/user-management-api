package com.lds.usermanagement.service;

import com.devertelo.springswaggercodegen3.model.UserRequest;
import com.devertelo.springswaggercodegen3.model.UserResponse;
import com.devertelo.springswaggercodegen3.model.UserStatus;
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
        usersEntity.setId(UUID.randomUUID().toString());
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
        return usersRepository.findById(id).map(this::toResponse).orElse(null);
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
        // mapping de endereços: se UserRequest possuir lista de endereços, converta para AddressEntity e adicione
        return usersEntity;
    }

    private UserResponse toResponse(UsersEntity usersEntity) {
        UserResponse r = new UserResponse();
        r.setId(usersEntity.getId());
        r.setUsername(usersEntity.getUsername());
        r.setName(usersEntity.getName());
        r.setLastName(usersEntity.getLastName());
        r.setEmail(usersEntity.getEmail());
        r.setPassword(usersEntity.getPassword());
        r.setStatus(UserStatus.valueOf(usersEntity.getStatus()));
        r.setCreatedAt(usersEntity.getCreatedAt());
        // mapear endereços se o modelo UserResponse suportar
        return r;
    }
}