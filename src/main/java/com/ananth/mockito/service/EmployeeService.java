package com.ananth.mockito.service;

import java.util.List;

import com.ananth.mockito.entity.Employee;

public interface EmployeeService {

	public Employee getEmployeeByName(String name);

	public Employee getEmployee(Long id);

	public List<Employee> getEmployeeByAddress(String name);

	public Employee addEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public List<Employee> getEmployees();

	public void deleteEmployee(Employee employee);
}
