package com.aplicacao.Agregadorinvestimento.controller;

import com.aplicacao.Agregadorinvestimento.dto.StockDTO;
import com.aplicacao.Agregadorinvestimento.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/stocks")
public class StockController {

    @Autowired
    private StockService service;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody StockDTO dto){
        service.createStock(dto);

        return ResponseEntity.ok().build();
    }
}
