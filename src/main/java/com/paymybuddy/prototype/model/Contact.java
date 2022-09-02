package com.paymybuddy.prototype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int contactId;

    @Column(name = "sender_account_id")
    private int senderAccountId;

    @Column(name = "receiver_account_id")
    private float receiverAccountId;

    public int getContactId() {
	return contactId;
    }

    public void setContactId(int contactId) {
	this.contactId = contactId;
    }

    public int getSenderAccountId() {
	return senderAccountId;
    }

    public void setSenderAccountId(int senderAccountId) {
	this.senderAccountId = senderAccountId;
    }

    public float getReceiverAccountId() {
	return receiverAccountId;
    }

    public void setReceiverAccountId(float receiverAccountId) {
	this.receiverAccountId = receiverAccountId;
    }

}
