package com.example.kafkaRate.services;

import com.example.kafkaRate.DTO.CurrencyRateDTO;
import com.example.kafkaRate.kafka.MsgService;
import com.example.kafkaRate.kafka.ValutesListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyService {

    private MsgService msgService;
    private ValutesListener listener;
    @Autowired
    public CurrencyService(MsgService msgService, ValutesListener listener) {
        this.msgService = msgService;
        this.listener = listener;
    }

    public double getExchangeRate(CurrencyRateDTO currencyRate) {
        msgService.sendRate(currencyRate);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return listener.getRate();
    }

}
