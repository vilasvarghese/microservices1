package com.companyservice.employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeServiceTest {

	@Mock
	private EmployeeDAORepository empRepository;
	
	@InjectMocks
	private EmployeeService empService;
	
	@Test
	void testGetAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1, "Vilas", 10000, "Hexaware"));
		when(empRepository.findAll()).thenReturn(empList);

		Iterable<Employee> resultEmpList = empService.getAllEmployees("Hexaware");
		Iterator<Employee> iterator = resultEmpList.iterator();
		while(iterator.hasNext()) {
			Employee emp = iterator.next();
			assertEquals("Vilas", emp.getName(), "Name doesn't match");
		}
	}
}
