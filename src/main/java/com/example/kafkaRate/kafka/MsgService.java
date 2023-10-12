package com.example.kafkaRate.kafka;

import com.example.kafkaRate.DTO.CurrencyRateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MsgService {
    @Value(value = "${topic}")
    private String topic;
    private final KafkaTemplate<String, CurrencyRateDTO> rateTemplate;
    @Autowired
    public MsgService(KafkaTemplate<String, CurrencyRateDTO> rateTemplate) {
        this.rateTemplate = rateTemplate;
    }

    public void sendRate(CurrencyRateDTO rate) {
        rateTemplate.send(topic, rate);
        log.info("Message sent");
    }
}
