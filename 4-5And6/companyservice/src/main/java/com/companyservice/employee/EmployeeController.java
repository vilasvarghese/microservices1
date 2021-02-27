package com.companyservice.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.companyservice.Company;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/companies/{companyName}/employees")
	public List<Employee> getEmployees(@PathVariable String companyName){
		return employeeService.getAllEmployees(companyName);	
	}
	
	@RequestMapping("/companies/{companyName}/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable String companyName, @PathVariable int id){
		Optional<Employee> optonalEmp = employeeService.getEmployee(id)	;
		return optonalEmp;
	}

	@RequestMapping(method=RequestMethod.POST, value="/companies/{companyName}/employees")
	public void createEmployee(@PathVariable String companyName, @RequestBody Employee emp){
		//Without this user would need to mention the company in the json while passing.
		//That can be confusing to the user..
		emp.setCompany(new Company(companyName, "",0));
		employeeService.createEmployee(emp);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/companies/{companyName}/employees/{id}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable String companyName, @PathVariable String id) {
		System.out.println("inside updateEmployee");
		employee.setCompany(new Company(companyName, "",0));
		employeeService.updateEmployee(id, employee);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{id}")
	public void deleteEmployee(@PathVariable String id){
		System.out.println("Inside deleteEmployee");
		employeeService.deleteEmployee(id);
	}
	
}
