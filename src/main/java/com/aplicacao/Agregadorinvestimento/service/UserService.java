package com.aplicacao.Agregadorinvestimento.service;


import com.aplicacao.Agregadorinvestimento.dto.AccountDTO;
import com.aplicacao.Agregadorinvestimento.dto.AccountResponseDTO;
import com.aplicacao.Agregadorinvestimento.dto.UserDTO;
import com.aplicacao.Agregadorinvestimento.entity.Account;
import com.aplicacao.Agregadorinvestimento.entity.BillingAddress;
import com.aplicacao.Agregadorinvestimento.entity.User;
import com.aplicacao.Agregadorinvestimento.repository.AccountRepository;
import com.aplicacao.Agregadorinvestimento.repository.BillingAddressRepository;
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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    @Transactional
    public User createUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
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
    public User updateUser(String userId, UserDTO dto) {
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

    @Transactional
    public Account createAccount(String userId, AccountDTO dto) {
        User user = repository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setStreet(dto.street());
        billingAddress.setNumber(dto.number());

        Account account = new Account();
        account.setDescription(dto.description());
        account.setUser(user);

        var accountUser = accountRepository.save(account);

        return accountUser;
    }

    public List<AccountResponseDTO> getAccounts(String userId) {
        User user = repository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Account> accounts = user.getAccounts();
        return user.getAccounts().stream().map(ac -> new AccountResponseDTO(ac.getAccountId().toString(), ac.getDescription())).collect(Collectors.toList());
    }
}
