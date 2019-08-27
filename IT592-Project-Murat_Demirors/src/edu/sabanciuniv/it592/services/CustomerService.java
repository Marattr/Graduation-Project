package edu.sabanciuniv.it592.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.sabanciuniv.it592.domain.Customer;

@Stateless
public class CustomerService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void removeCustomer(int customerId) {
		Customer customer=entityManager.find(Customer.class, customerId);
		entityManager.remove(customer);
	}
	
	public Customer getCustomerById(int id) {
		return entityManager.find(Customer.class, id);
	}
	
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);
	}
}
