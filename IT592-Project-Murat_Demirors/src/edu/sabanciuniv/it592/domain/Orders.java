package edu.sabanciuniv.it592.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double totalbill;	
	private String hospital;
	private String doctorName;
	private String doctorCode;
	private String paymentType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderdate;
	
	@ManyToOne
	private Customer customerOrder;
	
	@ManyToOne
	private Shop shopOrder;
	
	@ManyToOne
	private ShopPersonel personelOrder;
	
	@OneToMany(mappedBy="productOrder",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Product>products=new ArrayList<>();
	

	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalbill() {
		return totalbill;
	}

	public void setTotalbill(double totalbill) {
		this.totalbill = totalbill;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public Customer getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(Customer customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Shop getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(Shop shopOrder) {
		this.shopOrder = shopOrder;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public ShopPersonel getPersonelOrder() {
		return personelOrder;
	}

	public void setPersonelOrder(ShopPersonel personelOrder) {
		this.personelOrder = personelOrder;
	}


	
}
