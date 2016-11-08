/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeService {
	protected EntityManagerFactory emf;

	public EmployeeService() {
		this(new HashMap<String,String>());
	}

	public EmployeeService(Map<String, String> properties) {
		addConnectionProperties(properties);
		emf = Persistence.createEntityManagerFactory("employee", properties);
		initIfDatabaseEmpty();
	}
	
	private void initIfDatabaseEmpty() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Long numEmployees = (Long)em
				.createQuery("select count(e) from Employee e")
				.getSingleResult();
			if (0 == numEmployees) {
				// Inefficient but it's a small data set for a demo
				for (Employee emp : EmployeeInit.getInitialEmployees()) {
					addEmployee(emp);
				}
			}
		} finally {
			if (null != em) {
				em.close();
			}
		}	

		
	}

	protected void addConnectionProperties(Map<String, String> properties) {
		String DBAAS_DEFAULT_CONNECT_DESCRIPTOR = System.getenv("DBAAS_DEFAULT_CONNECT_DESCRIPTOR");
		String DBAAS_USER_NAME = System.getenv("DBAAS_USER_NAME");
		String DBAAS_USER_PASSWORD = System.getenv("DBAAS_USER_PASSWORD");
		properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@" + DBAAS_DEFAULT_CONNECT_DESCRIPTOR);
		properties.put("javax.persistence.jdbc.user", DBAAS_USER_NAME);
		properties.put("javax.persistence.jdbc.password", DBAAS_USER_PASSWORD);
	}
	
	public List<Employee> searchEmployeesByName(String name) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			List<Employee> result = em
				.createNamedQuery("Employee.findByName", Employee.class)
				.setParameter("name", name)
				.getResultList();
			return result;
		} finally {
			if (null != em) {
				em.close();
			}
		}
	}

	public Employee getEmployee(long id) throws Exception {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Employee.class, id);
		} finally {
			if (null != em) {
				em.close();
			}
		}
	}

	public long addEmployee(Employee employee) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
			return employee.getId();
		} finally {
			if (null != em) {
				em.close();
			}
		}
	}

	public boolean updateEmployee(Employee customer) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(customer);
			em.getTransaction().commit();
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		} finally {
			if (null != em) {
				em.close();
			}
		}
	}
		

	public boolean deleteEmployee(long id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Employee employee = em.find(Employee.class, id);
			if (null != employee) {
				em.remove(employee);
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
			return (null != employee);
		} finally {
			if (null != em) {
				em.close();
			}
		}
	}

	public List<Employee> getAllEmployees() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			List<Employee> result = em
				.createNamedQuery("Employee.findAll", Employee.class)
				.getResultList();
			return result;
		} finally {
			if (null != em) {
				em.close();
			}
		}	
	}


}
