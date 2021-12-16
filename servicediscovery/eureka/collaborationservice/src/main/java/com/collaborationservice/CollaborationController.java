package com.collaborationservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;


@RestController
public class CollaborationController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/catalog/hello")
	@HystrixCommand(fallbackMethod = "fallbackHello")
	public String collabHello() {
		return restTemplate.getForObject("http://COMPANYSERVICE/helloInCompanies", String.class);
	}
	
	public String fallbackHello() {
		return "Hello from fallback";
	}
	
	@RequestMapping("/catalog/companies/{companyName}")
	@HystrixCommand(fallbackMethod = "fallbackEmployeeRating")
	public List<EmployeeRatingCatalog> getEmployeesRating(@PathVariable String companyName){
		System.out.println("inside getEmployeesRating");
		CompanyEmployees compEmps = restTemplate.getForObject("http://COMPANYSERVICE/companies/"+companyName+"/employees", CompanyEmployees.class);
		
		List<Employee> empList = compEmps.getEmployeeList();
		List<EmployeeRatingCatalog> employeesRatingList = new ArrayList<EmployeeRatingCatalog>();
		for (Employee e : empList) {
			Rating r = restTemplate.getForObject("http://RATING/ratings/employeerating/"+e.getId(), Rating.class);
			employeesRatingList.add(new EmployeeRatingCatalog(e, r));
		}
		return employeesRatingList;
	}
	
	public List<EmployeeRatingCatalog> fallbackEmployeeRating(@PathVariable String companyName){
		System.out.println("inside fallback method");
		EmployeeRatingCatalog empRatingCatalogy = new EmployeeRatingCatalog();
		empRatingCatalogy.setEmployee(new Employee(1, "Vilas", 99999));
		empRatingCatalogy.setRating(new Rating("1", 5));
		 List<EmployeeRatingCatalog> empCatalogList = new ArrayList<EmployeeRatingCatalog>();
		 empCatalogList.add(empRatingCatalogy);
		 return empCatalogList;
	}

}
