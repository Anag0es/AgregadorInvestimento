package com.aplicacao.Agregadorinvestimento.client.dto;

public class TotalDTO {

    private Double regularMarketPrice;
    private String regularMarketDayRange;
    private double total;

    public TotalDTO(Double regularMarketPrice, String regularMarketDayRange, double total) {
        this.regularMarketPrice = regularMarketPrice;
        this.regularMarketDayRange = regularMarketDayRange;
        this.total = total;
    }

    public Double getRegularMarketPrice() {
        return regularMarketPrice;
    }

    public void setRegularMarketPrice(Double regularMarketPrice) {
        this.regularMarketPrice = regularMarketPrice;
    }

    public String getRegularMarketDayRange() {
        return regularMarketDayRange;
    }

    public void setRegularMarketDayRange(String regularMarketDayRange) {
        this.regularMarketDayRange = regularMarketDayRange;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
