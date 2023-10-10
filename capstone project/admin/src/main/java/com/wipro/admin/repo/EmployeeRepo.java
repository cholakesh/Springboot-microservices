package com.wipro.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.admin.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,String>{


	
}
