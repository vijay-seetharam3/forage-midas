package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class KafkaTransactionListener {
    @Value("${general.kafka-topic}")
    private String kafkaTopic;

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "${general.group-id}")
    public void listen(Transaction transaction) {
        try {
            System.out.println("Received transaction: " + transaction);
        } catch (Exception e) {
            System.err.println("error" + e.getMessage());
        }
    }
}