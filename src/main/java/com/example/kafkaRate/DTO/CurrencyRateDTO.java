package com.example.kafkaRate.DTO;

public record CurrencyRateDTO(String firstCurrency,
                              double amount,
                              String secondCurrency) {}
