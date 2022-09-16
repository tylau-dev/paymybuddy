package com.paymybuddy.prototype.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    public int getContactId() {
	return contactId;
    }

    public void setContactId(int contactId) {
	this.contactId = contactId;
    }

    public Account getSenderAccount() {
	return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
	this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
	return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
	this.receiverAccount = receiverAccount;
    }

    public Contact() {

    }

    public Contact(int contactId, Account senderAccount, Account receiverAccount) {
	super();
	this.contactId = contactId;
	this.senderAccount = senderAccount;
	this.receiverAccount = receiverAccount;
    }

}
