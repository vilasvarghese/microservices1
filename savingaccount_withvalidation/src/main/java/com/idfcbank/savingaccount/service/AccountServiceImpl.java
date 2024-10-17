package com.idfcbank.savingaccount.service;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.idfcbank.savingaccount.BankAccount;
import com.idfcbank.savingaccount.enums.AccountType;
import com.idfcbank.savingaccount.repo.AccountRespository;



@Service
public class AccountServiceImpl {


	@Autowired
	private AccountRespository accountRespository;

	//@Autowired
	//private RestTemplate restTemplate;

	//@org.springframework.beans.factory.annotation.Value("${api.auth}")
	//private String authUrl;

	
	/*public Optional<BankAccount> findByAadharNumberOrPanNumberOptional(String aadharNumber, String PanNumber){
		return accountRespository.findByAadharNumberOrPanNumberOptional(aadharNumber, PanNumber);
	}*/
	
	public Optional<BankAccount> findByPanNumber(String PanNumber){
		return accountRespository.findByPanNumber(PanNumber);
	}
	
	public Optional<BankAccount> findByCustomerId(Integer customerId){
		return accountRespository.findByCustomerId(customerId);
	}

	public List<BankAccount> findAccountsByBalanceRange(Double minBalance, Double maxBalance){
		return accountRespository.findAccountsByBalanceRange(minBalance.doubleValue(), maxBalance.doubleValue());
	}

	public List<BankAccount> findBankAccountsWithBalanceGreaterThan(Double balance){
		return accountRespository.findBankAccountsWithBalanceGreaterThan(balance.doubleValue());
	}
	
	
	public List<BankAccount> findByAccountStatusAndAccountType(Boolean accountStatus, AccountType accountType){
		return accountRespository.findByAccountStatusAndAccountType(accountStatus.booleanValue(), accountType);
	}
	
	public List<BankAccount> findByAccountStatus(Boolean accountStatus){
		return accountRespository.findByAccountStatus(accountStatus.booleanValue());
	}
	
	
	//@Override
	public BankAccount saveAccount(BankAccount account) {//throws AccountMappingException, IdNotFoundException 

		try {
			//ResponseEntity<User> user = restTemplate.getForEntity(authUrl + "/" + account.getCustomerId().toString(),User.class);
			//System.out.println(user);

			Optional<BankAccount> optional = accountRespository.findByCustomerId(account.getCustomerId());

			/*if (optional.isPresent())
				throw new AccountMappingException(
						"Provided User Already has Account. One user can have only one Account!!");
			*/
			return accountRespository.save(account);
		} catch (ResourceAccessException e) {
			// catch connection errors
			throw new ResourceAccessException("Problem connecting to server");
		} catch (HttpClientErrorException e) {
			// catch exceptions when http response code is 4xx or 5xx.
			//throw new IdNotFoundException("UserId not Found");
		} /*catch (AccountMappingException e) {
			throw new AccountMappingException(
					"Provided User Already has Account. One user can have only one Account!!");
		}*/
		return null;
	}

	//@Override
	public Optional<BankAccount> getAccount(String accountId) {
		return accountRespository.findById(accountId);
	}

	//@Override
	public Optional<BankAccount> getAccountByCustomerId(Integer customerId) {

		return accountRespository.findByCustomerId(customerId);
	}

	//@Override
	public List<BankAccount> getAccounts() {
		return (List<BankAccount>) accountRespository.findAll();
	}

	//@Override
	public BankAccount depositAmount(String accountId, long balance) {//throws IdNotFoundException, AccountCloseException 

		Optional<BankAccount> account = this.getAccount(accountId);

/*		if (account.isEmpty())
			throw new IdNotFoundException("Account Id Not found");
*/
		BankAccount retreivedAccount = account.get();
/*
		if (retreivedAccount.isAccountStatus() == false)
			throw new AccountCloseException("Account is Closed or suspended");
*/
		long accountBalance = retreivedAccount.getBalance();
		retreivedAccount.setBalance(accountBalance + balance);
		return accountRespository.save(retreivedAccount);

	}

	//@Override
	public BankAccount withdrawAmount(String accountId, long balance)
			{//throws IdNotFoundException, BalanceNotSufficientException, AccountCloseException 

		Optional<BankAccount> account = this.getAccount(accountId);

		/*if (account.isEmpty())
			throw new IdNotFoundException("Account Id Not found");
*/
		
		BankAccount retreivedAccount = account.get();
/*
		if (retreivedAccount.isAccountStatus() == false)
			throw new AccountCloseException("Account is Closed or suspended");

		if (retreivedAccount.getBalance() - balance < 10000)
			throw new BalanceNotSufficientException("minimum balance if 10000 has to be maintained");
*/
		long accountBalance = retreivedAccount.getBalance();
		retreivedAccount.setBalance(accountBalance - balance);
		return accountRespository.save(retreivedAccount);
	}

	//@Override
	public BankAccount closeAccount(String accountId) {//throws IdNotFoundException, AccountCloseException 

		Optional<BankAccount> account = this.getAccount(accountId);
/*
		if (account.isEmpty())
			throw new IdNotFoundException("Account Id Not found");
*/
		
		BankAccount retreivedAccount = account.get();
/*
		if (retreivedAccount.isAccountStatus() == false)
			throw new AccountCloseException("Provided Account is Already Closed!!");
*/
		retreivedAccount.setAccountStatus(false);

		return accountRespository.save(retreivedAccount);

	}

	//@Override
	public boolean deleteAccount(String accountId) {//throws IdNotFoundException 

		boolean isExists = accountRespository.existsById(accountId);
		/*if (!isExists)
			throw new IdNotFoundException("Account Id not found!");
*/
		accountRespository.deleteById(accountId);
		if (accountRespository.existsById(accountId))
			return false;
		else
			return true;
	}
	
	/*
	public List<BankAccount> getSortedAccount(String field){
		//return accountRespository.findAll(Sort.Direction.ASC, field);
		return accountRespository.findAll(
		
	}*/
	public List<BankAccount> getPaginatedAccounts(String field, int pageSize, int pageNo){
		return accountRespository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(field).descending())).getContent();
	}
}
//https://www.tutorialspoint.com/difference-between-crudrepository-and-jparepository-in-java