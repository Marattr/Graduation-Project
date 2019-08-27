package edu.sabanciuniv.it592.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.services.CustomerService;
import edu.sabanciuniv.it592.services.OrderService;

@ManagedBean
@ViewScoped
public class SalesBean implements Serializable{
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private CustomerService customerService;
	
	@Inject private LoginBean loginBean;
	
	private List<Orders>sales=new ArrayList<>();
	private List<Orders>filteredsales=new ArrayList<>();	
	private int customerId;
	private int orderId;
	
	
	@PostConstruct
	public void init() {		
		sales=orderService.getOrdersByShopname(loginBean.getShopname());	
	}
	
	public void setIds(int orderId,int customerId) {
		this.orderId=orderId;
		this.customerId=customerId;
	}
	
	public void deleteSale() {		
		orderService.deleteSale(orderId);		
		customerService.removeCustomer(customerId);
		init();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," ", "Sale deleted!"));
	}

	public List<Orders> getSales() {
		return sales;
	}

	public void setSales(List<Orders> sales) {
		this.sales = sales;
	}

	public List<Orders> getFilteredsales() {
		return filteredsales;
	}

	public void setFilteredsales(List<Orders> filteredsales) {
		this.filteredsales = filteredsales;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
	
}
