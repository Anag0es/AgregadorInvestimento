package com.aplicacao.Agregadorinvestimento.service;

import com.aplicacao.Agregadorinvestimento.dto.AccountStockDTO;
import com.aplicacao.Agregadorinvestimento.dto.AccountStockResponseDTO;
import com.aplicacao.Agregadorinvestimento.entity.AccountStock;
import com.aplicacao.Agregadorinvestimento.entity.AccountStockId;
import com.aplicacao.Agregadorinvestimento.repository.AccountRepository;
import com.aplicacao.Agregadorinvestimento.repository.AccountStockRepository;
import com.aplicacao.Agregadorinvestimento.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AccountStockRepository accountStockRepository;

    public AccountStockDTO associateStock(String accountId, AccountStockDTO dto) {
        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        var stock =  stockRepository.findById(dto.stockId()).orElseThrow(() -> new RuntimeException("Ação não encontrada"));

        // DTO -> ENTITY
        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        // ENTITY -> REPOSITORY
        accountStockRepository.save(entity);

        return dto;
    }


    public List<AccountStockResponseDTO> getStocks(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        return account.getAccountStocks().stream().map(as -> new AccountStockResponseDTO(
                as.getStock().getStockId(),
                as.getQuantity(),
                0.0
        )).toList();

    }
}
