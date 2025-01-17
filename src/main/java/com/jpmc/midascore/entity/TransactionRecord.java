package com.jpmc.midascore.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserRecord sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserRecord recipient;

    private Float amount;
    private Float incentive;
    private LocalDateTime timestamp;



    public TransactionRecord(UserRecord sender, UserRecord recipient, Float amount, float incentiveAmount, LocalDateTime timestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.incentive= incentiveAmount;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public UserRecord getSenderId() {
        return sender;
    }

    public UserRecord getRecipientId() {
        return recipient;
    }

    public float getAmount() {
        return amount;
    }

    public float getIncentive() {
        return incentive;
    }

    // Mutators
    public void setId(long id) {
        this.id = id;
    }

    public void setSenderId(UserRecord sender) {
        this.sender = sender;
    }

    public void setRecipientId(UserRecord recipient) {
        this.recipient = recipient;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setIncentive(float incentive) {
        this.incentive = incentive;
    }

    @Override
    public String toString() {
        return "TransactionRecord: [id: " + id + ", senderId: " + sender +
                ", recipientId: " + recipient + ", amount: " +
                amount + "]";
    }

}
