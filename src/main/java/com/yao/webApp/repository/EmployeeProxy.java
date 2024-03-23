package com.yao.webApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.yao.webApp.CustomProperties;
import com.yao.webApp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
	
  @Autowired
  private CustomProperties propertie;
  
  /**
   * Recupere tous les  employees
   * @return un  iterable de tous les  employees
   */
  
 public Iterable<Employee> getEmployees(){
	 
	 String baseApiUrl = propertie.getApiUrl(); 
	 String getEmployeeUrl = baseApiUrl + "/employees";
	 
	 RestTemplate restTemplate = new RestTemplate(); 
	 
	 ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
			 getEmployeeUrl, 
			 HttpMethod.GET,
			 null,
			 new ParameterizedTypeReference<Iterable<Employee>>() {}
			 );
	 
	 log.debug("Get employee call "+ response.getStatusCode().toString());

	return response.getBody();
	} 
 
 
 
 
public Employee createEmployee( Employee e){
	System.out.println("**************************web :_create_EmployeeRepository_*******************");

	 String baseApiUrl = propertie.getApiUrl(); 
	 String createEmployeeUrl = baseApiUrl + "/employee"; 
	 HttpEntity<Employee> request = new HttpEntity<Employee>(e);
	 
	 RestTemplate restTemplate = new RestTemplate(); 

	 ResponseEntity<Employee> response = restTemplate.exchange(
			 createEmployeeUrl, 
			 HttpMethod.POST,
			 request,
			 Employee.class
			 );

	 log.debug("Create employee call "+ response.getStatusCode().toString());

	return response.getBody();
	} 
 
 





/**
 * Recuperer un   employee par id
 * @param id l'id de l'employee
 * @return The employee which matches the id
 */
public Employee getEmployee(int id) {
	String baseApiUrl = propertie.getApiUrl();
	String getEmployeeUrl = baseApiUrl + "/employee/" + id;

	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Employee> response = restTemplate.exchange(
			getEmployeeUrl, 
			HttpMethod.GET, 
			null,
			Employee.class
		);
	
	log.debug("Get Employee call " + response.getStatusCode().toString());
	
	return response.getBody();
}



/**
 * Update de l'employee - avec PUT HTTP Method.
 * @param e  employee à update
 */
public Employee updateEmployee(Employee e) {
	String baseApiUrl = propertie.getApiUrl();
	String updateEmployeeUrl = baseApiUrl + "/employee/" + e.getId();

	RestTemplate restTemplate = new RestTemplate();
	HttpEntity<Employee> request = new HttpEntity<Employee>(e);
	ResponseEntity<Employee> response = restTemplate.exchange(
			updateEmployeeUrl, 
			HttpMethod.PUT, 
			request, 
			Employee.class);
	
	log.debug("Update Employee call " + response.getStatusCode().toString());
	
	return response.getBody();
}

/**
 * Suppression d'un employee 
 * instead of delete method in order to log the response status code.
 * @param e  employee a supprimer
 */
public void deleteEmployee(int id) {
	String baseApiUrl = propertie.getApiUrl();
	String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;
	
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Void> response = restTemplate.exchange(
			deleteEmployeeUrl, 
			HttpMethod.DELETE, 
			null, 
			Void.class);
	
	log.debug("Delete Employee call " + response.getStatusCode().toString());
}

}