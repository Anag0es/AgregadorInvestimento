package com.aplicacao.Agregadorinvestimento.controller;

import com.aplicacao.Agregadorinvestimento.dto.AccountDTO;
import com.aplicacao.Agregadorinvestimento.dto.AccountResponseDTO;
import com.aplicacao.Agregadorinvestimento.dto.UserDTO;
import com.aplicacao.Agregadorinvestimento.entity.Account;
import com.aplicacao.Agregadorinvestimento.entity.User;
import com.aplicacao.Agregadorinvestimento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
        User user = service.createUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = service.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = service.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody UserDTO dto) {
        User user = service.updateUser(userId, dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        service.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{userId}/accounts")
    public ResponseEntity<User> createAccount(@PathVariable String userId, @RequestBody AccountDTO dto) {
        Account userAccount = service.createAccount(userId, dto);
        return new ResponseEntity<>(userAccount.getUser(), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAccounts(@PathVariable String userId) {
        List<AccountResponseDTO> accounts = service.getAccounts(userId);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
