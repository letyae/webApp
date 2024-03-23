package com.yao.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.webApp.model.Employee;
import com.yao.webApp.repository.EmployeeProxy;

import lombok.Data;

@Service
@Data
public class EmployeeService {
	@Autowired
	private EmployeeProxy  employeeProxy;
	
	public Employee getEmployee(final int id) {
		return employeeProxy.getEmployee(id);
	}
	
	public Iterable<Employee> getEmployees() {
		return employeeProxy.getEmployees();
	}
	
	
	public void deleteEmployee(final int id) {
		 employeeProxy.deleteEmployee(id);
	}
	
	
	
	public Employee saveEmployee(Employee employee) {

		Employee saveEmployee = null;
    	
		//Nom de famille en majuscule
		employee.setLastName(employee.getLastName().toUpperCase());
		
		if(employee.getId()==null) { //nouvel employee

			employeeProxy.createEmployee(employee);

		}else {
			saveEmployee=employeeProxy.updateEmployee(employee);
		}
		
		return saveEmployee; 

	}

}
