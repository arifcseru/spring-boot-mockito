package com.arif.mockito.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.arif.mockito.entity.Employee;
import com.arif.mockito.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService service;

	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {

		Employee employee = new Employee();
		employee.setId(Long.valueOf(10121));
		employee.setName("arif");
		employee.setAddress("Test Address");

		given(service.getEmployee(employee.getId())).willReturn(employee);
		System.out.println("========================================");
		System.out.println("================= employee name: " + employee.getName() + "=======================");
		System.out.println("================= employee id: " + employee.getId() + "=======================");
		System.out.println("========================================");
		mvc.perform(get("/api/employee/" + employee.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("arif"));
	}

}
