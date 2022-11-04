package com.paymybuddy.prototype.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "transaction")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Column(name = "description")
    private String description;

    @Column(name = "transferred_amount")
    private float transferredAmount;

    @Column(name = "paid_amount")
    private float paidAmount;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;

    public int getTransactionId() {
	return transactionId;
    }

    public void setTransactionId(int transactionId) {
	this.transactionId = transactionId;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public float getTransferredAmount() {
	return transferredAmount;
    }

    public void setTransferredAmount(float transferredAmount) {
	this.transferredAmount = transferredAmount;
    }

    public float getPaidAmount() {
	return paidAmount;
    }

    public void setPaidAmount(float paidAmount) {
	this.paidAmount = paidAmount;
    }

    public Date getTransactionDate() {
	return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
	this.transactionDate = transactionDate;
    }

    public Contact getContact() {
	return contact;
    }

    public void setContact(Contact contact) {
	this.contact = contact;
    }

    public Transaction() {
    }

    public Transaction(int transactionId, Contact contact, String description, float transferredAmount,
	    float paidAmount, Date transactionDate) {
	super();
	this.transactionId = transactionId;
	this.contact = contact;
	this.description = description;
	this.transferredAmount = transferredAmount;
	this.paidAmount = paidAmount;
	this.transactionDate = transactionDate;
    }

}
