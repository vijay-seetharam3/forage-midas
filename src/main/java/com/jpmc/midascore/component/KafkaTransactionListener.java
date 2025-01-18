package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableKafka
public class KafkaTransactionListener {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;


    @Autowired
    private IncentiveService incentiveService;


    @KafkaListener(topics = "${general.kafka-topic}", groupId = "${general.group-id}")
    public void listen(Transaction transaction) {
        try {
            System.out.println("Received transaction: " + transaction);

            // Retrieve sender and recipient from the database
            UserRecord sender = userRepository.findById(transaction.getSenderId());
            UserRecord recipient = userRepository.findById(transaction.getRecipientId());

            // Validate transaction
            if (sender != null && recipient != null && sender.getBalance() >= transaction.getAmount()) {
                // Deduct from sender
                Incentive incentive = incentiveService.fetchIncentive(transaction);

                sender.setBalance(sender.getBalance()- transaction.getAmount());
                recipient.setBalance(recipient.getBalance() + transaction.getAmount() + incentive.getAmount());

                // Save updated balances
                userRepository.save(sender);
                userRepository.save(recipient);

                // Record transaction
                TransactionRecord transactionRecord = new TransactionRecord(sender, recipient, transaction.getAmount(),incentive.getAmount(), LocalDateTime.now());
                transactionRecordRepository.save(transactionRecord);

                System.out.println("Transaction Successful: " + sender.getName() + ": "
                        + sender.getBalance() + " "
                        + recipient.getName() + ": "
                        + recipient.getBalance()+" "
                        + transaction.getAmount() +
                        " Incentive: " + incentive.getAmount());
                        System.out.println("Transaction processed successfully.");
            } else {
                System.out.println("Invalid transaction. Discarding.");
            }
        } catch (Exception e) {
            System.err.println("Error processing transaction: " + e.getMessage());
        }
    }
}
