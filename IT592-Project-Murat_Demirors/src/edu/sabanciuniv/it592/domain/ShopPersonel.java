package edu.sabanciuniv.it592.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ShopPersonel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String tc_id_number;
	private String name;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String phonenumber;
	
	@ManyToOne
	private Shop shop;
	
	@OneToMany(mappedBy="productPersonel",cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	@OneToMany(mappedBy="personelOrder",cascade = CascadeType.ALL)
	private List<Orders>orders=new ArrayList<>();
			
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTc_id_number() {
		return tc_id_number;
	}
	public void setTc_id_number(String tc_id_number) {
		this.tc_id_number = tc_id_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
}
