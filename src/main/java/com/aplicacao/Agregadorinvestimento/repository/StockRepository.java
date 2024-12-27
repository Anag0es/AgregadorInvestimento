package com.aplicacao.Agregadorinvestimento.repository;

import com.aplicacao.Agregadorinvestimento.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
