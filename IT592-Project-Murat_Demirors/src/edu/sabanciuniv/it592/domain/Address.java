package edu.sabanciuniv.it592.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Shop shopAddress;
	
	@ManyToOne
	private ShopPersonel shopPersonelAddress;
	
	@ManyToOne 
	private Customer customerAddress;
	
	private String country;
	private String city;
	private String district;
	private String street;
	private String postcode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Shop getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(Shop shopAddress) {
		this.shopAddress = shopAddress;
	}
	public ShopPersonel getShopPersonelAddress() {
		return shopPersonelAddress;
	}
	public void setShopPersonelAddress(ShopPersonel shopPersonelAddress) {
		this.shopPersonelAddress = shopPersonelAddress;
	}
	public Customer getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(Customer customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
	
}
