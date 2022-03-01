package com.arif.mockito.service;

import com.arif.mockito.service.EmployeeService;
import com.arif.mockito.service.EmployeeServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.arif.mockito.entity.Employee;
import com.arif.mockito.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() {
		Employee employee = new Employee();
		employee.setName("arif");

		Mockito.when(employeeRepository.findByName(employee.getName())).thenReturn(employee);
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String name = "arif";
		Employee found = service.getEmployeeByName(name);

		Assert.assertEquals(found.getName(), name);
	}

	@Test
	public void getEmployeesTest() {
		Employee employee1 = new Employee();
		employee1.setId(Long.valueOf(1));
		employee1.setName("arif");
		employee1.setAddress("Test Address");

		Employee employee2 = new Employee();
		employee2.setId(Long.valueOf(1));
		employee2.setName("jalil");
		employee2.setAddress("Test Address");

		when(employeeRepository.findAll()).thenReturn(Stream.of(employee1, employee2).collect(Collectors.toList()));
		assertEquals(2, service.getEmployees().size());
	}

	@Test
	public void getEmployeebyAddressTest() {

		String address = "Dhaka";
		Employee employee1 = new Employee();
		employee1.setId(Long.valueOf(1));
		employee1.setName("arif");
		employee1.setAddress(address);

		when(employeeRepository.findByAddress(address)).thenReturn(Stream.of(employee1).collect(Collectors.toList()));
		assertEquals(1, service.getEmployeeByAddress(address).size());
	}

	@Test
	public void saveEmployeeTest() {
		Employee employee = new Employee();
		employee.setId(Long.valueOf(1));
		employee.setName("arif");
		employee.setAddress("Test Address");

		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, service.addEmployee(employee));
	}

	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee();
		employee.setId(Long.valueOf(1));
		employee.setName("arif");
		employee.setAddress("Test Address");
		Long deletedId = employee.getId();

		employeeRepository.delete(employee);
		// verify(employeeRepository, times(1)).delete(employee);
		assertNull(service.getEmployee(deletedId));
	}
}
