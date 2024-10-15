package bankaccount;

import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.AccountException;

public class SavingsAccount extends BankAccount{
	
	List<BankAccount> accoutList = Arrays.asList(new SavingsAccount(1), new SavingsAccount(2),new SavingsAccount(3)  );

			private int accountId;
    public List<BankAccount> getAccoutList() {
		return accoutList;
	}

	public void setAccoutList(List<BankAccount> accoutList) {
		this.accoutList = accoutList;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	private double balance;

    public SavingsAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
    	if (amount > 0) {
    		balance += amount;
    		System.out.println("balance "+balance);
    	}else {
    		System.out.println("You cannot deposit in negative. Kindly use withdrawal slip");
    	}
    }

    // Implementing the abstract methods
    @Override
    void withdraw(double amount) throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds.");
            throw new InsufficientBalanceException();
        }
    }

    @Override
    double getBalance() {
        return balance;
    }
    
    public static void main(String[] args) throws Exception{
        // We cannot instantiate an abstract class directly
        // BankAccount account = new BankAccount(); // ERROR

        // Create an object of the subclass
        SavingsAccount myAccount = new SavingsAccount(5000);
        
        myAccount.deposit(1500);  // Concrete method in abstract class
        myAccount.withdraw(2000); // Implemented abstract method in subclass
        System.out.println("Balance: " + myAccount.getBalance()); // Implemented abstract method
    }
}
