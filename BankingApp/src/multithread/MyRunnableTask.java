package multithread;

public class MyRunnableTask {
    public void run() {
        // Code to be executed by the thread
        System.out.println("Running task: " + Thread.currentThread().getName());
        
        BankAccount myAccount = new BankAccount(0);
        myAccount.withdraw(1000);
    }
}
