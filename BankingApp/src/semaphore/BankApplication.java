package semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

public class BankApplication {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        int maxConcurrentTransactions = 3;
        Semaphore semaphore = new Semaphore(maxConcurrentTransactions);
        CountDownLatch latch = new CountDownLatch(5); // Assuming 5 customer transactions

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.submit(new CustomerTransaction(account, "Customer " + (i + 1), semaphore, latch));
        }

        executor.shutdown();
        try {
            latch.await(); // Wait for all transactions to complete
            System.out.println("All transactions completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}

/*
 * 
Semaphore: 
	The Semaphore is used to 
		limit the number of concurrent transactions. 
	It has an 
		initial permit count of 
			maxConcurrentTransactions, 
			allowing only that many transactions to proceed at a time.
CountDownLatch: 
	The CountDownLatch is used to wait for all transactions to complete 
		before closing the bank's transaction processing. 
	It starts with a count equal to the number of transactions and 
		is decremented by each transaction upon completion.
CustomerTransaction: 
	The CustomerTransaction class now takes Semaphore and CountDownLatch as parameters. 
	It acquires a permit from the semaphore before 
		executing transactions and 
		releases it after completion. 
	It also signals the completion of the transaction to the latch.
Main Method: 
	The main method creates a 
		Semaphore and 
		CountDownLatch 
			with appropriate initial values. 
	It submits 
		customer transactions to the executor service and 
			then waits for the CountDownLatch to count down to zero, 
			indicating that all transactions have completed.
*/
