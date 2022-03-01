package com.arif.mockito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arif.mockito.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public Employee findByName(String name);

	@Query("SELECT emp FROM Employee emp WHERE  emp.id = :id")
	Employee findOne(@Param("id") Long id);

	public List<Employee> findByAddress(String address);

}
