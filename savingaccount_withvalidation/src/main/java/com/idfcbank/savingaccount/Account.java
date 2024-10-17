package com.idfcbank.savingaccount;

import org.springframework.stereotype.Component;

@Component
public class Account {

	
    protected int accountNumber;
    protected double balance;
    protected int accountType;//1 - Saving, 2 - Current, 3 - LoanAccount

    
    public Account() {
		super();
		this.accountNumber = 1;
		this.balance = 92375923;
		this.accountType = 1;
	}



	public int getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	public int getAccountType() {
		return accountType;
	}



	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}



	//protected Customer customer;
    // Method to display account information
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber + ", Balance: " + balance);
        //customer.displayCustomerInfo();  // Display associated customer info
    }

    
    
    public String toString() {
    	return "accountNumber "+this.accountNumber + ", balance "+this.balance + ", accounttype "+this.accountType;
    }
    
    public void print() {
    	System.out.println(this);
    }
    
}