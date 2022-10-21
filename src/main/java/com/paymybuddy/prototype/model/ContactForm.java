package com.paymybuddy.prototype.model;

public class ContactForm {
    private String receiverEmail;

    public String getReceiverEmail() {
	return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
	this.receiverEmail = receiverEmail;
    }

    public ContactForm(String receiverEmail) {
	super();
	this.receiverEmail = receiverEmail;
    }

    public ContactForm() {
    }

}
