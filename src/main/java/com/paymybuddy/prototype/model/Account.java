package com.paymybuddy.prototype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int accountId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "balance")
    private float balance;

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

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public float getBalance() {
	return balance;
    }

    public void setBalance(float balance) {
	this.balance = balance;
    }
}
