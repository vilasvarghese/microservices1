
public class Account {

	
    protected int accountNumber;
    protected double balance;
    protected int accountType;//1 - Saving, 2 - Current, 3 - LoanAccount
    //protected Customer customer;

    // Constructor to initialize account attributes
    public Account(int accountNumber, double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        //this.customer = customer;
    }

    public Account(int accountNumber, double balance, int accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
	}

	// Method to deposit money (overloaded)
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    // Overloaded method for deposit that accepts a custom message
    public void deposit(double amount, String message) {
        balance += amount;
        System.out.println("Deposited: " + amount + " (" + message + "), New Balance: " + balance);
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    
    // Method to display account information
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber + ", Balance: " + balance);
        //customer.displayCustomerInfo();  // Display associated customer info
    }

    
    public static void main(String args[]) {
    	Account account = new Account(1, 2000, new Customer("Vilas" , 1, "Testing") );
    	account.displayAccountInfo();
    }
    
    
    public String toString() {
    	return "accountNumber "+this.accountNumber + ", balance "+this.balance + ", accounttype "+this.accountType;
    }
    
    public void print() {
    	System.out.println(this);
    }
    
}
