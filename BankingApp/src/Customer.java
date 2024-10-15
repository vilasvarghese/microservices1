import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {

    private String name;
    private int id;
    private String address;
    private List<Account> accountList;
    
    
    public List<Account> getAccoutList() {
		return accountList;
	}

	public void setAccoutList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public void addAccount(Account account) {
		this.accountList.add(account);
	}
	
	// Constructor to initialize the attributes
    public Customer(String name, int id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    // Getters (for accessing the attributes)
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    // Setters (for modifying the attributes)
    public void setAddress(String address) {
        this.address = address;
    }

    // Method to display customer details
    public void displayCustomerInfo() {
        System.out.println("Customer ID: " + id + ", Name: " + name + ", Address: " + address);
        Iterator<Account> iterator = accountList.iterator();
        while (iterator.hasNext()) {
        	System.out.println(iterator.next());
        }
    }
	
    
    public static void main(String args[]) {
    	Customer customer = new Customer("Vilas" , 1, "Testing");
    	List<Account> vilasAccountList = new ArrayList<Account>();
    	vilasAccountList.add(new Account(1, 55555, 1));
    	vilasAccountList.add(new Account(2, 234234, 2));
    	customer.setAccoutList(vilasAccountList);
    	customer.displayCustomerInfo();
    }
}
