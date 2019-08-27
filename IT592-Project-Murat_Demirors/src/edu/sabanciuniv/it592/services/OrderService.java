package edu.sabanciuniv.it592.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.sabanciuniv.it592.domain.Customer;
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Product;

@Stateless
public class OrderService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Orders> getOrdersByShopname(String shopname){
		List<Orders>orders=entityManager.createQuery("select o from Orders o",Orders.class).getResultList();
		List<Orders>result=new ArrayList<>();
		
		for(Orders order:orders) {
			if(order.getShopOrder().getShop_name().equals(shopname)) {
				result.add(order);
			}
		}
		
		return result;
	}
	
	public Orders getOrderInfo(String shopname,int customerId){
		List<Orders>orders=entityManager.createQuery("select o from Orders o",Orders.class).getResultList();
		List<Orders>result=new ArrayList<>();
		Orders customerOrder=new Orders();
		for(Orders order:orders) {
			if(order.getShopOrder().getShop_name().equals(shopname)) {
				result.add(order);
			}
		}
		
		for(Orders order:result) {
			if(order.getCustomerOrder().getId()==customerId) {
				customerOrder=order;
			}
		}
		
		return customerOrder;
	}
	
	public List<Product>getOrderProducts(int orderId){
		List<Orders>orders=entityManager.createQuery("select o from Orders o",Orders.class).getResultList();
		List<Product>products=new ArrayList<>();
		for(Orders order:orders) {
			if(order.getId()==orderId) {
				products=order.getProducts();
			}
		}
		return products;
	}
	
	public Orders getOrderById(int id) {
		return entityManager.find(Orders.class, id);
	}
	
	public void deleteSale(int orderId) {
		Orders sale=entityManager.find(Orders.class, orderId);
		entityManager.remove(sale);
	}
	
	public void updateOrder(Orders order) {
		entityManager.merge(order);
	}
	
	public boolean saveProducts(Product product) {
		entityManager.persist(product);
		return true;
	}
	
	public boolean saveCustomer(Customer customer) {
		entityManager.persist(customer);
		return true;
	}
	
	public boolean saveOrder(Orders order) {
		entityManager.persist(order);
		return true;
	}
	
	
}
