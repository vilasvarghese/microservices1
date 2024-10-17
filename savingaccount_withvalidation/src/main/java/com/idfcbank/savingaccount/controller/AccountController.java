package com.idfcbank.savingaccount.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idfcbank.savingaccount.BankAccount;
import com.idfcbank.savingaccount.enums.AccountType;
import com.idfcbank.savingaccount.exceptions.AccountMappingException;
import com.idfcbank.savingaccount.exceptions.IdNotFoundException;
import com.idfcbank.savingaccount.service.AccountServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountService;

	//@Autowired
	//private RequestToEntityMapper requestToEntityMapper;

	//@Autowired
	//private EntityToResponseMapper entityToResponseMapper;

	
	//@Valid will force a validation 
	@PostMapping()//"/create"
	public ResponseEntity<?> createAccount(@Valid @RequestBody BankAccount accountRequest)
			throws IdNotFoundException, AccountMappingException{ 

		//BankAccount account;
		//account = requestToEntityMapper.accountRequestToEntity(accountRequest);
		System.out.println(accountRequest);

		/*AccountResponse createdProfile = entityToResponseMapper
				.accountToAccountResponse(accountService.saveAccount(account));
		return new ResponseEntity<AccountResponse>(createdProfile, HttpStatus.CREATED);*/
		
		return new ResponseEntity<BankAccount>(accountService.saveAccount(accountRequest), HttpStatus.CREATED);
	}

	@GetMapping("/{accountId}")
	public ResponseEntity<?> getAccount(@PathVariable("accountId") String accountId) throws IdNotFoundException {

		Optional<BankAccount> account = accountService.getAccount(accountId);

		if (account.isEmpty())
			throw new IdNotFoundException("ProfileId Not Found!!");

		//AccountResponse accountResponse = entityToResponseMapper.accountToAccountResponse(account.get());

		return ResponseEntity.ok(account.get());
		//return account.get();
	}

/*	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAccountByUserId(@PathVariable("userId") Integer userId) throws IdNotFoundException {

		Optional<Account> account = accountService.getAccountByCustomerId(userId);

		if (account.isEmpty())
			throw new IdNotFoundException("User does not have an account!!");

		AccountResponse accountResponse = entityToResponseMapper.accountToAccountResponse(account.get());

		return ResponseEntity.ok(accountResponse);
	}
*/
	
	@GetMapping()//"/all"
	public ResponseEntity<?> getAccounts() {

		List<BankAccount> accounts = accountService.getAccounts();
		
		
		
		/*//List<AccountResponse> accountResponses;
		accountResponses = accounts.stream().map(account -> entityToResponseMapper.accountToAccountResponse(account))
				.collect(Collectors.toList());*/

		return ResponseEntity.ok(accounts);
		//return ResponseEntity.status(HttpStatus.CREATED).body(accounts);
		//return accounts;
	}

	@PutMapping("/deposit/{accountId}")
	public BankAccount deposit(@PathVariable("accountId") String accountId,
			@RequestBody BankAccount amountRequest) {//throws IdNotFoundException, AccountCloseException 

		BankAccount account = accountService.depositAmount(accountId, amountRequest.getBalance());
		return account;
		//return ResponseEntity.ok(account);
	}

	@PutMapping("/withdraw/{accountId}")
	public BankAccount withdraw(@PathVariable("accountId") String accountId,
			@RequestBody BankAccount amountRequest)
			{//throws IdNotFoundException, BalanceNotSufficientException, AccountCloseException 

		BankAccount account = accountService.withdrawAmount(accountId, amountRequest.getBalance());
		return account;
		//return ResponseEntity.ok(account);
	}

	@DeleteMapping("/{accountId}")
	public String closeAccount(@PathVariable("accountId") String accountId)
			{//throws IdNotFoundException, AccountCloseException 

		BankAccount account = accountService.closeAccount(accountId);
		return "Account Deleted succesfully";
		//return ResponseEntity.ok(account);
	}
	/*
	@GetMapping("/getaadhar/pan/{aadharNumber}/{PanNumber}")
	public Optional<BankAccount> findByAadharNumberOrPanNumberOptional(@PathVariable String aadharNumber, @PathVariable String PanNumber){
		return accountService.findByAadharNumberOrPanNumberOptional(aadharNumber, PanNumber);
	}*/
	
	@GetMapping("/getpan/{PanNumber}")
	public BankAccount findByPanNumber(@PathVariable String PanNumber){
		return accountService.findByPanNumber(PanNumber).get();
	}
	
	@GetMapping("/custId/{customerId}")
	public BankAccount findByCustomerId(@PathVariable Integer customerId){
		return accountService.findByCustomerId(customerId).get();
	}

	@GetMapping("/range/{minBalance}/{maxBalance}")
	public List<BankAccount> findAccountsByBalanceRange(@PathVariable Double minBalance, @PathVariable  Double maxBalance){
		return accountService.findAccountsByBalanceRange(minBalance, maxBalance);
	}

	@GetMapping("/greater/{balance}")
	public List<BankAccount> findBankAccountsWithBalanceGreaterThan(@PathVariable Double balance){
		return accountService.findBankAccountsWithBalanceGreaterThan(balance);
	}
	
	@GetMapping("/statustype/{accountStatus}/{accountType}")	
	public List<BankAccount> findByAccountStatusAndAccountType(@PathVariable boolean accountStatus, @PathVariable AccountType accountType){
		return accountService.findByAccountStatusAndAccountType(accountStatus, accountType);
	}
	
	@GetMapping("/status/{accountStatus}")
	public List<BankAccount> findByAccountStatus(@PathVariable boolean accountStatus){
		return accountService.findByAccountStatus(accountStatus);
	}


/*	@DeleteMapping("/{accountId}")
	public ResponseEntity<?> deleteAcountById(@PathVariable("accountId") String accountId) throws IdNotFoundException {

		accountService.deleteAccount(accountId);
		return ResponseEntity.noContent().build();
	}
*/

	@GetMapping("/page")
	public List<BankAccount> getPaginatedAccount(
			@RequestParam(value= "pageNo", defaultValue = "1", required = false) Integer pageNo,
			@RequestParam(value= "pageSize", defaultValue = "2", required = false) Integer pageSize
			){
		
		return accountService.getPaginatedAccounts("balance", pageNo, pageSize);
	}
}

