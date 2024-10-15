package multithread;

public class CustomerTransaction implements Runnable {
    private BankAccount account;
    private String name;

    public CustomerTransaction(BankAccount account, String name) {
        this.account = account;
        this.name = name;
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
}
