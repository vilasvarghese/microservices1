package com.idfcbank.savingaccount;

import com.idfcbank.savingaccount.enums.AccountType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class BankAccount {

	public BankAccount() {
		super();
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public boolean isAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	private Long accountId;

	private boolean accountStatus = true;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "accountType cannot be null")
	private AccountType accountType;

	@NotBlank(message = "Pan Number cannot be blank")
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN Number")
	private String panNumber;

	@NotBlank(message = "Aadhaar Number cannot be blank")
	@Pattern(regexp = "^\\d{12}$", message = "Invalid Aadhar Number")
	//^\d{4}-\d{4}-\d{4}$ - 4digit-4digit-4digit
	private String aadharNumber;

	@NotBlank(message = "Contact Details cannot be blank")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid Contact Number")
	private String contact;

	@Min(value = 10000, message = "Minimum Balance is 10000")
	private long balance;
	
	@NotNull
	private Integer customerId;

	
}

/*
Spring Boot Validation: 
	A Comprehensive Overview
Spring Boot 
	provides powerful validation 
		mechanisms to 
			ensure 
				data integrity and 
				consistency in your applications. 
				
1. Built-in Validation Annotations
Spring Boot offers a set of built-in annotations to validate input parameters and entity fields:

	@NotNull: Ensures that a field cannot be null.
	@NotEmpty: Ensures that a string field is not empty (i.e., it has at least one non-whitespace character).
	@NotBlank: Similar to @NotEmpty, but also trims leading and trailing whitespace before checking for emptiness.
	@Min and @Max: Specifies minimum and maximum values for numeric fields.
	@Size: Validates the size of a collection or string.
	@Pattern: Validates a string against a regular expression pattern.
	@Future and @Past: 
		Validates 
			dates and times 
				against future or past constraints.
	@Email: Validates email addresses.
	@URL: Validates URLs.
Example:

Java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @Email   
    private String email;   


    // ... other fields
}
Use code with caution.

2. Custom Validation Annotations
You can create custom validation annotations to define specific validation rules. Here's an example:

Java
@Constraint(validatedBy = {PositiveOrZeroValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveOrZero {
    String message() default "{javax.validation.constraints.PositiveOrZero.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default   
 {};
}
Use code with caution.

3. Method-Level Validation
You can validate method parameters using the @Valid annotation. 
This will trigger validation on 
	all annotated parameters before the method is executed.

Example:

Java
@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    // ...
}
Use code with caution.

4. Validation Groups
You can group validation constraints into different groups to enforce different validation rules based on the context.

Example:

Java
@NotNull(groups = {Create.class})
private String firstName;

@NotNull(groups = {Update.class})
private String lastName;

public interface Create {}
public interface Update {}
Use code with caution.

5. Error Handling and Messaging
You can customize error messages using the message attribute in validation annotations. You can also use Spring's BindingResult object to access validation errors in your controller methods.

Example:

Java
@PostMapping("/users")
public ResponseEntity<String> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors())   
 {
        return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());   

    }
    // ...
}


@Valid:

	Purpose: This annotation is used to trigger validation on an object or parameter. It instructs Spring to validate the annotated object or parameter against its associated constraints.
	Usage:
	Annotate an object or parameter with @Valid to initiate validation.
	Typically used in conjunction with other validation annotations (e.g., @NotNull, @Size, @Email) to validate nested objects or method parameters.

@Target({ElementType.FIELD, ElementType.METHOD}):

	Purpose: This annotation specifies the valid locations where the annotated element can be used.
	Usage:
	Annotate a custom annotation with @Target to specify where it can be applied.
	The ElementType enum provides options like FIELD, METHOD, TYPE, PARAMETER, etc.

*/