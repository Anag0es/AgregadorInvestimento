package com.aplicacao.Agregadorinvestimento.service;

import com.aplicacao.Agregadorinvestimento.dto.StockDTO;
import com.aplicacao.Agregadorinvestimento.entity.Stock;
import com.aplicacao.Agregadorinvestimento.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;


    public void createStock(StockDTO dto) {

        // DTO -> Entity
        Stock stock = new Stock(
                dto.stockId(),
                dto.description()
        );

        repository.save(stock);
    }
}
