package com.example.kafkaRate.DTO;

public record RequestedRateDTO(String reqId,
                               String reqDate,
                               double value) {}
