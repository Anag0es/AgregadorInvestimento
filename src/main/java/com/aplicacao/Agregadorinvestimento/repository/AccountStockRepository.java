package com.aplicacao.Agregadorinvestimento.repository;

import com.aplicacao.Agregadorinvestimento.entity.AccountStock;
import com.aplicacao.Agregadorinvestimento.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
