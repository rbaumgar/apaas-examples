package com.example.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeInit {
	
	public static List<Employee> getInitialEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("John","Smith","12-12-1980","Manager","Sales","john.smith@abc.com"));
		employees.add(new Employee("Laura","Adams","02-11-1979","Manager","IT","laura.adams@abc.com"));	
		employees.add(new Employee("Peter","Williams","22-10-1966","Coordinator","HR","peter.williams@abc.com"));	
		employees.add(new Employee("Joana","Sanders","11-11-1976","Manager","Marketing","joana.sanders@abc.com"));	
		employees.add(new Employee("John","Drake","18-08-1988","Coordinator","Finance","john.drake@abc.com"));	
		employees.add(new Employee("Samuel","Williams","22-03-1985","Coordinator","Finance","samuel.williams@abc.com"));	
		return employees;
	}
	
	public static void main(String[] args) {
		Map<String, String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		properties.put("eclipselink.ddl-generation.output-mode", "database");
		EmployeeService empService = new EmployeeService(properties);
		
		for (Employee emp : getInitialEmployees()) {
			empService.addEmployee(emp);
		}
	}
}
