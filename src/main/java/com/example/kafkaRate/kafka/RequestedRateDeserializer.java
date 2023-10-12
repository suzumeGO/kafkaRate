package com.example.kafkaRate.kafka;

import com.example.kafkaRate.DTO.RequestedRateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class RequestedRateDeserializer implements Deserializer<RequestedRateDTO> {
    @Override
    public RequestedRateDTO deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(data, RequestedRateDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to RequestedRateDTO");
        }
    }
}
