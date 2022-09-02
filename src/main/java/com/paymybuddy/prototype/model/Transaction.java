package com.paymybuddy.prototype.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int transactionId;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "receiver_id")
    private int receiverId;

    @Column(name = "description")
    private String description;

    @Column(name = "transferred_amount")
    private float transferredAmount;

    @Column(name = "paid_amount")
    private float paidAmount;

    @Column(name = "transaction_date")
    private Date transactionDate;
}
