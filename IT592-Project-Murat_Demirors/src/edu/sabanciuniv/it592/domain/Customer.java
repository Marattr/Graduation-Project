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
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tc_id_number;
	private String name;
	private String lastname;
	private String phonenumber;
	
	@ManyToOne
	private Shop shopCustomer;
	
	@OneToMany(mappedBy="customerOrder",cascade = CascadeType.ALL)
	private List<Orders>orders=new ArrayList<>();
	
	@OneToMany(mappedBy="customerAddress",cascade = CascadeType.ALL)
	private List<Address>customerAddresses=new ArrayList<>();
	
	public Customer() {
		
	}

	public Customer(String tc_id_number, String name, String lastname, String phonenumber) {
		super();
		this.tc_id_number = tc_id_number;
		this.name = name;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
	}

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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Shop getMemberCustomer() {
		return shopCustomer;
	}

	public void setMemberCustomer(Shop memberCustomer) {
		this.shopCustomer = memberCustomer;
	}

	public Shop getShopCustomer() {
		return shopCustomer;
	}

	public void setShopCustomer(Shop shopCustomer) {
		this.shopCustomer = shopCustomer;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}


	
}
