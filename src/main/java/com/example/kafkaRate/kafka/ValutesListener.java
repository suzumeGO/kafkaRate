package com.example.kafkaRate.kafka;

import com.example.kafkaRate.DTO.RequestedRateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Component
public class ValutesListener {
    private final Set<String> valutes = new TreeSet<>(Comparator.naturalOrder());
    private double rate;

    @KafkaListener(topics = "converter.valutes", groupId = "val")
    public void handleValute(String valute) {
        valutes.add(valute);
    }

    @KafkaListener(topics = "converter.requestedRates",
            groupId = "reqRate",
            containerFactory = "rateKafkaListenerContainerFactory")
    public void handleRate(RequestedRateDTO rateDTO) {
        log.info(rateDTO.reqId() + " " + rateDTO.value());
        rate = rateDTO.value();

    }

    public Set<String> getValutes() {
        return valutes;
    }


    public double getRate() {
        if (rate != 0) {
            log.info("Rate obtained");
        } else {
            log.info("Rate obtain failed");
        }
        return rate;
    }
}
