package com.companyservice;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import com.companyservice.employee.Employee;

@Service
public class CompanyService {
	
	static int count = 0;

	@Autowired
	private CompanyDAORepository companyDAORepository;
	
	public List<Company> getCompanies(){
		List<Company> companyList = new ArrayList<Company>();
		companyDAORepository.findAll().forEach(companyList::add);
		return companyList;
		/*
		 * This was used in Step 2
		 * return Arrays.asList(
		 
			new Company("Infosys", "Infosys India Pvt Ltd.", 100000),
			new Company("Oracle", "Oracle India Pvt Ltd.", 50000),
			new Company("MI", "MobileIron India Pvt Ltd.", 850)
		);*/
	}
	
	private void addDelay(){
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Optional<Company> getCompany(String name) {
		System.out.println("count "+count);
		count++;
		if (count %10 ==0){
			addDelay();
		}
		return companyDAORepository.findById(name);
	}
	
	public void updateCompany(String name, Company company) {
		companyDAORepository.save(company);
	}

	public void deleteCompany(String name) {
		companyDAORepository.deleteById(name);
	}

	
	public void addCompany(Company company) {
		companyDAORepository.save(company);
	}


}
