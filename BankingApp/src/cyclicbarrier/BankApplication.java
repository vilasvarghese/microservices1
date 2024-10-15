package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

public class BankApplication {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        int maxConcurrentTransactions = 3;
        Semaphore semaphore = new Semaphore(maxConcurrentTransactions);
        CountDownLatch latch = new CountDownLatch(5); // Assuming 5 customer transactions
        CyclicBarrier barrier = new CyclicBarrier(5); // Assuming 5 customer transactions

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.submit(new CustomerTransaction(account, "Customer " + (i + 1), semaphore, latch, barrier));
        }

        executor.shutdown();
        try {
            latch.await(); // Wait for all transactions to complete
            System.out.println("All transactions completed.");
            barrier.await(); // Wait for all threads to reach this point for batch processing
            System.out.println("Batch processing started.");
            // Perform batch processing here (e.g., update account balances, generate reports)
            System.out.println("Batch processing completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (BrokenBarrierException bbe) {
        	bbe.printStackTrace();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}


/*
CyclicBarrier: 
	The CyclicBarrier 
		used to synchronize multiple threads 
			at a specific point, 
		allowing for 
			batch processing after all transactions have completed.
CustomerTransaction: 
	The CustomerTransaction class 
		now takes CyclicBarrier as a parameter. 
	After completing its transactions, 
		it waits at the barrier for all other transactions to finish.
Main Method: 
	The main method 
		creates a CyclicBarrier with a 
			count equal to the number of customer transactions. 
	It waits for the barrier to reach this count before initiating batch processing.
*/