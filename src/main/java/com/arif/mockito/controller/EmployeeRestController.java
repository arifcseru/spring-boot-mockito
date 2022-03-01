package com.arif.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arif.mockito.entity.Employee;
import com.arif.mockito.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		System.out.println("========================");
		System.out.println("id: " + id);
		Employee employee = employeeService.getEmployee(id);
		System.out.println("========================");
		return employee;
	}

	@PostMapping("/employee")
	public Employee createNewEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee oldEmployee = employeeService.getEmployee(id);
		oldEmployee.setAddress(employee.getAddress());
		oldEmployee.setName(employee.getName());

		return employeeService.updateEmployee(oldEmployee);
	}

}
