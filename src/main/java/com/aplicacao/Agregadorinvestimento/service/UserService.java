package com.aplicacao.Agregadorinvestimento.service;


import com.aplicacao.Agregadorinvestimento.dto.CreateUserDTO;
import com.aplicacao.Agregadorinvestimento.entity.User;
import com.aplicacao.Agregadorinvestimento.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User createUser(CreateUserDTO createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.username());
        user.setEmail(createUserDto.email());
        user.setPassword(createUserDto.password());
        return repository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        return repository.findById(UUID.fromString(userId));
    }

    public List<User> getUsers() {
        var users = repository.findAll();
        return users.stream().map(user -> {
            return user;
        }).collect(Collectors.toList());
    }

    @Transactional
    public User updateUser(String userId, CreateUserDTO dto) {
        User user = repository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return repository.save(user);
    }

    @Transactional
    public void deleteUser(String userId) {
        var id = UUID.fromString(userId);

        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        repository.deleteById(id);
    }
}
