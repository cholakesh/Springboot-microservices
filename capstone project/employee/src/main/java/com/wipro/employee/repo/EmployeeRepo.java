package com.wipro.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.employee.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,String>{


	
}
