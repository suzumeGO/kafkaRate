package com.example.kafkaRate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyRate {
    private String firstCurrency;
    private double amount;
    private String secondCurrency;
    private double value;
}
