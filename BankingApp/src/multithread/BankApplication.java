package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankApplication {
    public static void main(String[] args) {
        //BankAccount account = new BankAccount(1000);
        ThreadSafeBankAccount account = new ThreadSafeBankAccount (1000);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            //executor.submit(new CustomerTransaction(account, "Customer " + (i + 1)));
        	 executor.submit(new ThreadSafeCustomerTransaction(account, "Customer " + (i + 1)));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        System.out.println("Final balance: " + account.getBalance());
    }
}
