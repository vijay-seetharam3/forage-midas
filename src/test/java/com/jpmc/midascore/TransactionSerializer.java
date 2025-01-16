package com.jpmc.midascore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.midascore.foundation.Transaction;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TransactionSerializer implements Serializer<Transaction> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Transaction transaction) {
        try {
            // Convert the Transaction object to JSON (byte array)
            return objectMapper.writeValueAsBytes(transaction);
        } catch (Exception e) {
            // Log the error if serialization fails
            System.err.println("ERROR: Failed to serialize transaction: " + e.getMessage());
            throw new RuntimeException("Error serializing Transaction", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No configuration needed for this serializer
    }

    @Override
    public void close() {
        // No resources to close
    }
}
