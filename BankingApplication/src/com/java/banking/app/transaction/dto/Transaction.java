package com.java.banking.app.transaction.dto;

import java.text.DateFormatSymbols;
import java.util.Date;

public class Transaction {

	private Date TransactionDate;
	private String Vendor;
	private String Type;
	private double Amount;
	private String Category;

	public Date getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}

	public String getVendor() {
		return Vendor;
	}

	public void setVendor(String vendor) {
		Vendor = vendor;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	@Override
	public String toString() {
		return "Transaction [TransactionDate=" + TransactionDate + ", Vendor=" + Vendor + ", Type=" + Type + ", Amount="
				+ Amount + ", Category=" + Category + "]";
	}

	public String getMonth() {
		return new DateFormatSymbols().getMonths()[getTransactionDate().getMonth()];

	}

}
