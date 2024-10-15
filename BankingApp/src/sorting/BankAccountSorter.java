package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccountSorter {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        accounts.add(new BankAccount("A123", 1000));
        accounts.add(new BankAccount("B456", 500));
        accounts.add(new BankAccount("C789", 2000));

        Collections.sort(accounts, new BalanceComparator());

        System.out.println("Accounts sorted by balance:");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }
}
