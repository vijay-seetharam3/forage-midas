package com.jpmc.midascore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.midascore.foundation.Transaction;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TransactionDeserializer implements Deserializer<Transaction> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Transaction deserialize(String topic, byte[] data) {
        try {
            // Convert the byte array back into a Transaction object
            String payload = new String(data);  // Optional: Log the raw payload for debugging

            // Deserialize the byte array into the Transaction object
            return objectMapper.readValue(data, Transaction.class);
        } catch (Exception e) {
            // Log the error if deserialization fails
            System.err.println("ERROR: Failed to deserialize data from topic '" + topic + "': " + e.getMessage());
            throw new RuntimeException("Error deserializing Transaction", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No configuration needed for this deserializer
    }

    @Override
    public void close() {
        // No resources to close
    }
}
