package edu.sabanciuniv.it592.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String shop_name;
	private String tax_id_number;
	
	@OneToMany(mappedBy="shop",cascade = CascadeType.ALL)
	private List<ShopPersonel>personnels=new ArrayList<>();
	
	@OneToMany(mappedBy="shopCustomer",cascade = CascadeType.ALL)
	private List<Customer>customers=new ArrayList<>();
	
	@OneToMany(mappedBy="shopOrder",cascade = CascadeType.ALL)
	private List<Orders>orders=new ArrayList<>();	
	
	@OneToMany(mappedBy="shopProduct",cascade = CascadeType.ALL)
	private List<Product>products=new ArrayList<>();
	
	public Shop() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<ShopPersonel> getPersonnels() {
		return personnels;
	}

	public void setPersonnels(List<ShopPersonel> personnels) {
		this.personnels = personnels;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getTax_id_number() {
		return tax_id_number;
	}

	public void setTax_id_number(String tax_id_number) {
		this.tax_id_number = tax_id_number;
	}
	
	
}
