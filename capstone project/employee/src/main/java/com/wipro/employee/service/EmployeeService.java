package com.wipro.employee.service;

import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.employee.entity.Employee;
import com.wipro.employee.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo empRepo;

	public void saveEmpDetails(String userName, String assessment, LocalDate date, String type) throws ParseException {
		
		Employee emp= new Employee();
		emp.setUserName(userName);
		emp.setAssessment(assessment);
		emp.setDate(date);
		emp.setType(type);
		
		empRepo.save(emp);
		
	}

}
