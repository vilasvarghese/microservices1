package semaphore;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        lock.lock(); 

        try {
            balance += amount;
            System.out.println("Depositing " + amount + " to account: " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawing  " + amount + " from account: " + Thread.currentThread().getName());
            } else {
                System.out.println("Insufficient funds for withdrawal: " + Thread.currentThread().getName());
            }
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
