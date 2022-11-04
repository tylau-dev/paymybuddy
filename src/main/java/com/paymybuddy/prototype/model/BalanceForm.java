package com.paymybuddy.prototype.model;

public class BalanceForm {
    private float currentBalance;
    private float balancetoAdd;

    public float getCurrentBalance() {
	return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
	this.currentBalance = currentBalance;
    }

    public float getBalancetoAdd() {
	return balancetoAdd;
    }

    public void setBalancetoAdd(float balancetoAdd) {
	this.balancetoAdd = balancetoAdd;
    }

    public BalanceForm() {

    }

    public BalanceForm(float currentBalance, float balancetoAdd) {
	super();
	this.currentBalance = currentBalance;
	this.balancetoAdd = balancetoAdd;
    }

}
