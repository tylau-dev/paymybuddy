package com.paymybuddy.prototype.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_name")
    private String accountName;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance")
    private float balance;

    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> senderContacts = new ArrayList<>();

    @OneToMany(mappedBy = "receiverAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> receiverContacts = new ArrayList<>();

    public int getAccountId() {
	return accountId;
    }

    public void setAccountId(int accountId) {
	this.accountId = accountId;
    }

    public String getAccountName() {
	return accountName;
    }

    public void setAccountName(String accountName) {
	this.accountName = accountName;
    }

    public float getBalance() {
	return balance;
    }

    public void setBalance(float balance) {
	this.balance = balance;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Account() {
    }

    public Account(int accountId, String accountName, User user, float balance) {
	super();
	this.accountId = accountId;
	this.accountName = accountName;
	this.user = user;
	this.balance = balance;
    }

}
