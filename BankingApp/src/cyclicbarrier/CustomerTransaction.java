package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CustomerTransaction  implements Callable<Void> {
    private BankAccount account;
    private String name;
    private Semaphore semaphore;
    private CountDownLatch latch;
    private CyclicBarrier barrier;

    public CustomerTransaction(BankAccount account, String name, Semaphore semaphore, CountDownLatch latch, CyclicBarrier barrier) {
        this.account = account;
        this.name = name;
        this.semaphore = semaphore;
        this.latch = latch;
        this.barrier = barrier;
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
            barrier.await(); // Wait for all transactions to complete before batch processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch(BrokenBarrierException bbe) {
        	bbe.printStackTrace();
        }
        finally {
            semaphore.release(); // Release the permit
            latch.countDown(); // Signal that the transaction is complete
        }
        return null;
    }
}
