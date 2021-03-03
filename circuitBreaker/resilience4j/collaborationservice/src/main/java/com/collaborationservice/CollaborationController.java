package com.collaborationservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CollaborationController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/catalog/companies/{companyName}")
	@CircuitBreaker(name = "COMPANY-RATING-COLLABORATION", fallbackMethod = "fallbackEmpRating")
	public List<EmployeeRatingCatalog> getEmployeesRating(@PathVariable String companyName){
		CompanyEmployees compEmps = restTemplate.getForObject("http://COMPANYSERVICE/companies/"+companyName+"/employees", CompanyEmployees.class);
		List<Employee> empList = compEmps.getEmployeeList();
		List<EmployeeRatingCatalog> employeesRatingList = new ArrayList<EmployeeRatingCatalog>();
		for (Employee e : empList) {
			Rating r = restTemplate.getForObject("http://RATING/ratings/employeerating/"+e.getId(), Rating.class);
			employeesRatingList.add(new EmployeeRatingCatalog(e, r));
		}
		return employeesRatingList;
	}
	
	public List<EmployeeRatingCatalog> fallbackEmpRating(@PathVariable String companyName, Exception e){
		List<EmployeeRatingCatalog> employeesRatingList = new ArrayList<EmployeeRatingCatalog>();
		employeesRatingList.add(
				new EmployeeRatingCatalog(
						new Employee(1, "Vilas", 10000, "Oracle"), new Rating("Vilas", 5)));
		
		return employeesRatingList;
	}
}
