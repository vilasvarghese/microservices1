package com.companyservice.employee;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

/****************************************************************
/Start eurekaserver before you start this..
 * 
 *
 *****************************************************************/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

	// bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	///
	void testSeedData() throws Exception{
	
		ResponseEntity<Void> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/seedData").toString(), Void.class);
		//assertEquals("Data seed successful", response.getBody());
		/*ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/companies/Hexaware/employees").toString(), String.class);
	    //assertEquals("Hello Controller", response.getBody());
		*/
	}
	
	@Test
	void testGetEmployees() throws Exception{
		

	}

}
