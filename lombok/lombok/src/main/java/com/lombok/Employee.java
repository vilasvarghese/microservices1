package com.lombok;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Employee {

	private int empId;
	private String name;
	private String address;
	private String department;
	
}
