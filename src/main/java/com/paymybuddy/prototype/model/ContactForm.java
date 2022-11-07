package com.paymybuddy.prototype.model;

import java.util.List;

public class ContactForm {
    private String receiverEmail;
    private String currentEmail;

    public String getReceiverEmail() {
	return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
	this.receiverEmail = receiverEmail;
    }

    public String getCurrentEmail() {
	return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
	this.currentEmail = currentEmail;
    }

    public ContactForm(String receiverEmail, String currentEmail, List<Contact> currentUserContacts) {
	super();
	this.receiverEmail = receiverEmail;
	this.currentEmail = currentEmail;
    }

    public ContactForm() {
    }

}
