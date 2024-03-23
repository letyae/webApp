package com.yao.webApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yao.webApp.model.Employee;
import com.yao.webApp.service.EmployeeService;

import lombok.Data;


@Controller
@Data
public class EmployeeController {
  
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employee> listEmployees=service.getEmployees();
		model.addAttribute("employees", listEmployees);
		return "home";
	} 
	
	@GetMapping("/createEmployee")
	public String createEmployee(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "formNewEmployee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
		service.deleteEmployee(id);
		return new ModelAndView("redirect:/");
		}
	
	/*
	@GetMapping("/employeeForm")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "formNewEmployee";
    } */
	
	@PostMapping("/saveEmployee")
	//@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
    	System.out.println("**************************Web:_Save_EmployeeController_*******************");

		  service.saveEmployee(employee);
		  System.out.println("Nom: " + employee. getFirstName());
	        System.out.println("Prénom: " + employee.getLastName());
	        System.out.println("Mot de passe: " + employee.getPassword());
	        
		return new ModelAndView("redirect:/");
	}
	
	
	
	
	
	
}
