package semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CustomerTransaction implements Callable<Void> {
    private BankAccount account;
    private String name;
    private Semaphore semaphore;
    private CountDownLatch latch;

    public CustomerTransaction(BankAccount account, String name, Semaphore semaphore, CountDownLatch latch) {
        this.account = account;
        this.name = name;
        this.semaphore = semaphore;
        this.latch = latch;
    }

    public Void call() {
        try {
            semaphore.acquire(); // Acquire a permit
            for (int i = 0; i < 3; i++) {
                if (Math.random() < 0.5) {
                    account.deposit(100);
                } else {
                    account.withdraw(50);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Release the permit
            latch.countDown(); // Signal that the transaction is complete
        }
        return null;
    }
}
