package com.example.kafkaRate.kafka;

import com.example.kafkaRate.DTO.CurrencyRateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class CurrencyRateSerializer implements Serializer<CurrencyRateDTO> {

    @Override
    public byte[] serialize(String topic, CurrencyRateDTO data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (data == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing CurrencyRateDTO to byte[]");
        }
    }
}
