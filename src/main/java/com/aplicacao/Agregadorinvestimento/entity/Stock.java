package com.aplicacao.Agregadorinvestimento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_stocks")
public class Stock {

    @Id
    @Column(name = "stock_id")
    private String stockId; // PETR4, VALE3, etc

    @Column(name = "description")
    private String Description;

    public Stock() {
    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        Description = description;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
