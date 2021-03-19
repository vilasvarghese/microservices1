package com.lombok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private Employee emp;
	
	@RequestMapping("/lombok")
	public String getEmployee() {
		emp.setEmpId(1);
		emp.setName("Vilas");
		emp.setDepartment("IT");
		emp.setAddress("Bangalore");
		return emp.toString();
	}
	
}
