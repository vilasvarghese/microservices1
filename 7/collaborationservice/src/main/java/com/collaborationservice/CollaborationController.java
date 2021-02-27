package com.collaborationservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CollaborationController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/catalog/companies/{companyName}")
	public List<EmployeeRatingCatalog> getEmployeesRating(@PathVariable String companyName){
		
		/*CompanyEmployees compEmps = restTemplate.getForObject("http://localhost:8081/companies/"+companyName+"/employees", List.class);
		/*for (Employee emp : empList) {
			
		}*  /
		System.out.println(empList);
		//Employee e1 = (Employee)empList.get(0);
		int empListSize = empList.size();
		System.out.println(empListSize);
		List<EmployeeRatingCatalog> employeesRatingList = new ArrayList<EmployeeRatingCatalog>();
		System.out.println(employeesRatingList);
		for (int i =0; i < empListSize; i++) {
			System.out.println(empList.get(i));
			Employee e = empList.get(i);
			System.out.println(e);
			Rating r = restTemplate.getForObject("http://localhost:8082/employeerating/"+e.getId(), Rating.class);
			System.out.println(r);
			employeesRatingList.add(new EmployeeRatingCatalog(e, r));
		}
		return employeesRatingList;*/

		CompanyEmployees compEmps = restTemplate.getForObject("http://localhost:8081/companies/"+companyName+"/employees", CompanyEmployees.class);
		int empListSize = compEmps.getEmployeeList().size();
		List<EmployeeRatingCatalog> employeesRatingList = new ArrayList<EmployeeRatingCatalog>();
		for (int i =0; i < empListSize; i++) {
			Employee e = compEmps.getEmployeeList().get(i);
			Rating r = restTemplate.getForObject("http://localhost:8082/ratings/employeerating/"+e.getId(), Rating.class);
			employeesRatingList.add(new EmployeeRatingCatalog(e, r));
		}
		return employeesRatingList;
	
	
	}

}
