package com.idfcbank.savingaccount.repo;

import java.awt.print.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.idfcbank.savingaccount.BankAccount;
import com.idfcbank.savingaccount.enums.AccountType;

@Repository
public interface AccountRespository extends JpaRepository<BankAccount, String>{//CrudRepository<BankAccount, String> {

	// select * from Account where account.customerId = ?
	public Optional<BankAccount> findByCustomerId(Integer customerId);
	
	public List<BankAccount> findByAccountStatus(boolean accountStatus);
	public List<BankAccount> findByAccountStatusAndAccountType(boolean accountStatus, AccountType accountType);
	//public Optional<BankAccount> findByAadharNumberOrPanNumberOptional(String aadharNumber, String PanNumber);
	public Optional<BankAccount> findByPanNumber(String PanNumber);

	@Query("SELECT a FROM BankAccount a WHERE a.balance > ?1")
	public List<BankAccount> findBankAccountsWithBalanceGreaterThan(double balance);
	
    //@Query(value = "SELECT * FROM BankAccount a WHERE a.balance BETWEEN ?1 AND ?2", nativeQuery = true)
    //Native SQL: A direct SQL query that is executed against the underlying database.
	@Query("SELECT a FROM BankAccount a WHERE a.balance > ?1 and a.balance < ?2")
    public List<BankAccount> findAccountsByBalanceRange(double minBalance, double maxBalance);

}

//https://www.tutorialspoint.com/difference-between-crudrepository-and-jparepository-in-java