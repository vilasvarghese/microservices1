package com.idfcbank.savingaccount.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idfcbank.savingaccount.BankAccount;
import com.idfcbank.savingaccount.service.AccountServiceImpl;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountService;

	//@Autowired
	//private RequestToEntityMapper requestToEntityMapper;

	//@Autowired
	//private EntityToResponseMapper entityToResponseMapper;

	@PostMapping()//"/create"
	public String createAccount(@RequestBody BankAccount accountRequest)
			{//throws IdNotFoundException, AccountMappingException 

		//BankAccount account;
		//account = requestToEntityMapper.accountRequestToEntity(accountRequest);
		System.out.println(accountRequest);

		/*AccountResponse createdProfile = entityToResponseMapper
				.accountToAccountResponse(accountService.saveAccount(account));
		return new ResponseEntity<AccountResponse>(createdProfile, HttpStatus.CREATED);*/
		accountService.saveAccount(accountRequest);
		return "Account Successfully created";
	}

	@GetMapping("/{accountId}")
	public BankAccount getAccount(@PathVariable("accountId") String accountId) {//throws IdNotFoundException 

		Optional<BankAccount> account = accountService.getAccount(accountId);

		/*if (account.isEmpty())
			throw new IdNotFoundException("ProfileId Not Found!!");
*/
		//AccountResponse accountResponse = entityToResponseMapper.accountToAccountResponse(account.get());

		//return ResponseEntity.ok(accountResponse);
		return account.get();
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
	public List<BankAccount> getAccounts() {

		List<BankAccount> accounts = accountService.getAccounts();

		
		
		/*//List<AccountResponse> accountResponses;
		accountResponses = accounts.stream().map(account -> entityToResponseMapper.accountToAccountResponse(account))
				.collect(Collectors.toList());

		return ResponseEntity.ok(accountResponses);*/
		return accounts;
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

/*	@DeleteMapping("/{accountId}")
	public ResponseEntity<?> deleteAcountById(@PathVariable("accountId") String accountId) throws IdNotFoundException {

		accountService.deleteAccount(accountId);
		return ResponseEntity.noContent().build();
	}
*/
}

