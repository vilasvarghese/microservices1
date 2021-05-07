package com.companyservice.employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {EmployeeServiceTest.class}) @ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeDAORepository empRepository;
	
	@InjectMocks
	private EmployeeService empService;// = new EmployeeService();
	
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
