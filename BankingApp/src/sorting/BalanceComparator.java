package sorting;

import java.util.Comparator;

public class BalanceComparator  implements Comparator<BankAccount>{
    @Override
    public int compare(BankAccount account1, BankAccount account2) {
        return Double.compare(account1.getBalance(), account2.getBalance());

    }
}
