package edu.sabanciuniv.it592.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	private String brand;
	private String barcode_number;
	private Date addedDate;
	
	@ManyToOne
	private Category productCategory;
	
	@ManyToOne
	private Orders productOrder;
	
	@ManyToOne
	private Shop shopProduct;
	
	@ManyToOne
	private ShopPersonel productPersonel;		
	
	public Category getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}
	public Orders getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(Orders productOrder) {
		this.productOrder = productOrder;
	}

	public Shop getShopProduct() {
		return shopProduct;
	}
	public void setShopProduct(Shop shopProduct) {
		this.shopProduct = shopProduct;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBarcode_number() {
		return barcode_number;
	}
	public void setBarcode_number(String barcode_number) {
		this.barcode_number = barcode_number;
	}
	public ShopPersonel getProductPersonel() {
		return productPersonel;
	}
	public void setProductPersonel(ShopPersonel productPersonel) {
		this.productPersonel = productPersonel;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
}
