package com.aplicacao.Agregadorinvestimento.dto;

import com.aplicacao.Agregadorinvestimento.client.dto.TotalDTO;

public record AccountStockResponseDTO(String accountId, int quantity, TotalDTO total) {
}
