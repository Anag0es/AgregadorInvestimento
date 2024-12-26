package com.aplicacao.Agregadorinvestimento.repository;

import com.aplicacao.Agregadorinvestimento.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
