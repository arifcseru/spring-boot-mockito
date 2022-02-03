package com.ananth.mockito.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ananth.mockito.entity.Employee;
import com.ananth.mockito.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public Employee addEmployee(Employee user) {
		return employeeRepository.save(user);
	}

	@Override
	public Employee updateEmployee(Employee user) {
		return employeeRepository.save(user);
	}

	@Override
	public List<Employee> getEmployees() {
		Iterable<Employee> employees = employeeRepository.findAll();
		List<Employee> employeeList = new ArrayList<Employee>();
		for (Employee employee : employees) {
			employeeList.add(employee);
		}
		System.out.println("Getting data from DB : " + employeeList.size());
		return employeeList;
	}

	@Override
	public void deleteEmployee(Employee user) {
		employeeRepository.delete(user);
	}

	@Override
	public List<Employee> getEmployeeByAddress(String name) {
		return employeeRepository.findByAddress(name);
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findOne(id);
	}
}