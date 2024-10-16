package com.idfcbank.savingaccount.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.idfcbank.savingaccount.BankAccount;

@Repository
public interface AccountRespository extends CrudRepository<BankAccount, String> {

	// select * from Account where account.customerId = ?
	public Optional<BankAccount> findByCustomerId(Integer customerId);
}