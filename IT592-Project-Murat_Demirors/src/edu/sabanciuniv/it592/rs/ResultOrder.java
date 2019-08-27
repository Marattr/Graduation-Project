package edu.sabanciuniv.it592.rs;

import java.util.ArrayList;
import java.util.List;

public class ResultOrder {
	private int serviceMessageCode;
	private String serviceMessageText;
	private List<OrderProxy> orders=new ArrayList<>();
	
	
	public List<OrderProxy> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderProxy> orders) {
		this.orders = orders;
	}
	public int getServiceMessageCode() {
		return serviceMessageCode;
	}
	public void setServiceMessageCode(int serviceMessageCode) {
		this.serviceMessageCode = serviceMessageCode;
	}
	public String getServiceMessageText() {
		return serviceMessageText;
	}
	public void setServiceMessageText(String serviceMessageText) {
		this.serviceMessageText = serviceMessageText;
	}

	
	
	
}
