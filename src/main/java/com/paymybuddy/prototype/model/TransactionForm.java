package com.paymybuddy.prototype.model;

public class TransactionForm {
    private int contactId;
    private String description;
    private float transferredAmount;

    public int getContactId() {
	return contactId;
    }

    public void setContactId(int contactId) {
	this.contactId = contactId;
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

    public TransactionForm(int contactId, String description, float transferredAmount) {
	super();
	this.contactId = contactId;
	this.description = description;
	this.transferredAmount = transferredAmount;
    }

    public TransactionForm() {
    }
}
