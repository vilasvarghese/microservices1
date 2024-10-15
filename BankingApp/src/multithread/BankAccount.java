package multithread;

public class BankAccount {
	   private double balance;

	    public BankAccount(double initialBalance) {
	        this.balance = initialBalance;
	    }

	    public synchronized void deposit(double amount) {
	        balance += amount;
	        System.out.println("Depositing " + amount + " to account: " + Thread.currentThread().getName());
	    }

	    public synchronized void withdraw(double amount) {
	        if (balance >= amount) {
	            balance -= amount;
	            System.out.println("Withdrawing " + amount + " from account: " + Thread.currentThread().getName());
	        } else {
	            System.out.println("Insufficient funds for withdrawal: " + Thread.currentThread().getName());
	        }
	    }

	    public synchronized double getBalance() {
	        return balance;
	    }
}