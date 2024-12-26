package com.aplicacao.Agregadorinvestimento.controller;

import com.aplicacao.Agregadorinvestimento.dto.AccountStockDTO;
import com.aplicacao.Agregadorinvestimento.dto.AccountStockResponseDTO;
import com.aplicacao.Agregadorinvestimento.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<AccountStockDTO> associateStocks(@PathVariable String accountId, @RequestBody AccountStockDTO dto) {
        AccountStockDTO response = service.associateStock(accountId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> getAccount(@PathVariable String accountId) {
       var response = service.getStocks(accountId);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
