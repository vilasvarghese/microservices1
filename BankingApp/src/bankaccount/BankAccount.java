package bankaccount;

public abstract class BankAccount {

    void deposit(double amount) {
        System.out.println("Depositing: " + amount);
    }

    // Abstract method (no implementation here)
    abstract void withdraw(double amount) throws InsufficientBalanceException;

    // Abstract method
    abstract double getBalance();
}
