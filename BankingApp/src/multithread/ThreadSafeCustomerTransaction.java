package multithread;

public class ThreadSafeCustomerTransaction  implements Runnable{
    private ThreadSafeBankAccount account;
    private String name;

    public ThreadSafeCustomerTransaction(ThreadSafeBankAccount account, String name) {
        this.account = account;
        this.setName(name);
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            if (Math.random() < 0.5) {
                account.deposit(100);
            } else {
                account.withdraw(50);
            }
        }
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
