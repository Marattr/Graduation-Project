package edu.sabanciuniv.it592.mbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.Category;
import edu.sabanciuniv.it592.domain.Customer;
import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.CategoryService;
import edu.sabanciuniv.it592.services.OrderService;
import edu.sabanciuniv.it592.services.ProductService;
import edu.sabanciuniv.it592.services.UserService;


@ManagedBean
@ViewScoped
public class PrescriptionSaleBean implements Serializable{
	@EJB
	private UserService userService;
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private CategoryService categoryService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private AddressService addressService;
	
	@Inject LoginBean loginBean;
	
	private UIComponent mybutton;
	
	private Address address=new Address();
	private Customer customer=new Customer();
	private Orders order=new Orders();
	private Glass right_eye=new Glass();
	private Glass left_eye=new Glass();
	private Glass right_eye_reading=new Glass();
	private Glass left_eye_reading=new Glass();
	private EyeGlasses eyeglasses=new EyeGlasses();
	private EyeGlasses eyeglasses_reading=new EyeGlasses();
	private boolean right_eye_storage=false;
	private boolean left_eye_storage=false;
	private boolean right_eye_reading_storage=false;
	private boolean left_eye_reading_storage=false;
	private boolean eyeglasses_storage=false;
	private boolean eyeglasses_reading_storage=false;
	private boolean productsChecked=false;
	private boolean eyeglasses_available=false;
	private boolean reading_glasses_available=false;
	private String totalBill;

	public String changePage() {
		
		return null;
	}
	
	public String save() {
		
		Category glass=categoryService.getCategoryByName("Glass");
		Category eye_glasses=categoryService.getCategoryByName("EyeGlasses");		
		
		Shop shop=userService.getUserShop(loginBean.getUsername());
		
		order.setShopOrder(shop);
		customer.setShopCustomer(shop);
		order.setCustomerOrder(customer);
		try {
			order.setTotalbill(Double.valueOf(totalBill));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
					new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter total bill again!"));
			return null;
		}
		
		try {
			int tc=Integer.valueOf(customer.getTc_id_number());
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
					new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter customer TC ID again!"));
			return null;
		}
		
		right_eye.setProductCategory(glass);
		left_eye.setProductCategory(glass);
		right_eye_reading.setProductCategory(glass);
		left_eye_reading.setProductCategory(glass);
		eyeglasses.setProductCategory(eye_glasses);
		eyeglasses_reading.setProductCategory(eye_glasses);
		
		right_eye.setReading_glasses(false);
		left_eye.setReading_glasses(false);
		right_eye_reading.setReading_glasses(true);
		left_eye_reading.setReading_glasses(true);
		eyeglasses.setReading_glasses(false);
		eyeglasses_reading.setReading_glasses(true);
		
		right_eye.setRight_eye(true);
		left_eye.setRight_eye(false);
		right_eye_reading.setRight_eye(true);
		left_eye_reading.setRight_eye(false);
		
		if(eyeglasses_available) {
			
			
			right_eye.setProductOrder(order);
			right_eye.setShopProduct(shop);
			
			left_eye.setProductOrder(order);
			left_eye.setShopProduct(shop);
			
			if(right_eye_storage) {
				productService.removeProductFromStorage(loginBean.getShopname(),right_eye);
			}
			
			if(left_eye_storage) {
				productService.removeProductFromStorage(loginBean.getShopname(),left_eye);
			}
			
			if(eyeglasses_storage) {
				productService.removeProductFromStorage(loginBean.getShopname(),eyeglasses);
			}
			
			if((productService.checkProduct(right_eye)) || (productService.checkProduct(left_eye))) {
				FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
						new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode numbers again!"));
				return null;
				
			}else {	
				eyeglasses.setProductOrder(order);
				eyeglasses.setShopProduct(shop);		
				
				if(productService.checkProduct(eyeglasses)) {
					FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
							new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode numbers again!"));
					return null;				
				}else {
					productsChecked=true;
				}						
							
			}			
	
		}
		
		if(reading_glasses_available) {
			right_eye_reading.setProductOrder(order);
			right_eye_reading.setShopProduct(shop);
			
			left_eye_reading.setProductOrder(order);
			left_eye_reading.setShopProduct(shop);
			
			if(right_eye_reading_storage) {
				productService.removeProductFromStorage(loginBean.getShopname(),right_eye_reading);
			}
			
			if(left_eye_reading_storage) {
				productService.removeProductFromStorage(loginBean.getShopname(),left_eye_reading);
			}
			
			if(eyeglasses_reading_storage) {
				productService.removeProductFromStorage(loginBean.getShopname(),eyeglasses_reading);
			}
			
			if((productService.checkProduct(right_eye_reading)) || (productService.checkProduct(left_eye_reading))) {
				FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
						new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode numbers again!"));
				return null;
				
			}else {		
				eyeglasses_reading.setProductOrder(order);
				eyeglasses_reading.setShopProduct(shop);
									
				if(productService.checkProduct(eyeglasses_reading)) {
					FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
							new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode numbers again!"));
					return null;	
				}else {
					productsChecked=true;
				}								
			   
			}
		}
				
		if(productsChecked) {
			try {
				
				ShopPersonel personel=userService.getUserByName(loginBean.getUsername());
				order.setPersonelOrder(personel);
				orderService.saveCustomer(customer);
				orderService.saveOrder(order);
				address.setCustomerAddress(customer);
				addressService.saveAddress(address);
				
				if(eyeglasses_available) {					
					orderService.saveProducts(eyeglasses);
					orderService.saveProducts(right_eye);
					orderService.saveProducts(left_eye);
				}
				
				if(reading_glasses_available) {					
					orderService.saveProducts(eyeglasses_reading);
					orderService.saveProducts(right_eye_reading);
					orderService.saveProducts(left_eye_reading);
				}				
				
				FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
						new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful!", "Sale added!"));
				reloadData();
				return null;
				
			}catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!", "An error occurred!"));
				return null;
			}
				
		}
		FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!", "An error occurred!"));
		return null;
		
	}
	
	public void reloadData() {
		address=new Address();
		customer=new Customer();
		order=new Orders();
		right_eye=new Glass();
		left_eye=new Glass();
		right_eye_reading=new Glass();
		left_eye_reading=new Glass();
		eyeglasses=new EyeGlasses();
		eyeglasses_reading=new EyeGlasses();
	}

	
	
	public boolean isRight_eye_storage() {
		return right_eye_storage;
	}



	public void setRight_eye_storage(boolean right_eye_storage) {
		this.right_eye_storage = right_eye_storage;
	}



	public boolean isLeft_eye_storage() {
		return left_eye_storage;
	}



	public void setLeft_eye_storage(boolean left_eye_storage) {
		this.left_eye_storage = left_eye_storage;
	}



	public boolean isRight_eye_reading_storage() {
		return right_eye_reading_storage;
	}



	public void setRight_eye_reading_storage(boolean right_eye_reading_storage) {
		this.right_eye_reading_storage = right_eye_reading_storage;
	}



	public boolean isLeft_eye_reading_storage() {
		return left_eye_reading_storage;
	}



	public void setLeft_eye_reading_storage(boolean left_eye_reading_storage) {
		this.left_eye_reading_storage = left_eye_reading_storage;
	}



	public boolean isEyeglasses_storage() {
		return eyeglasses_storage;
	}



	public void setEyeglasses_storage(boolean eyeglasses_storage) {
		this.eyeglasses_storage = eyeglasses_storage;
	}



	public boolean isEyeglasses_reading_storage() {
		return eyeglasses_reading_storage;
	}



	public void setEyeglasses_reading_storage(boolean eyeglasses_reading_storage) {
		this.eyeglasses_reading_storage = eyeglasses_reading_storage;
	}



	public UIComponent getMybutton() {
		return mybutton;
	}



	public void setMybutton(UIComponent mybutton) {
		this.mybutton = mybutton;
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Glass getRight_eye_reading() {
		return right_eye_reading;
	}

	public void setRight_eye_reading(Glass right_eye_reading) {
		this.right_eye_reading = right_eye_reading;
	}

	public Glass getRight_eye() {
		return right_eye;
	}

	public void setRight_eye(Glass right_eye) {
		this.right_eye = right_eye;
	}

	public Glass getLeft_eye() {
		return left_eye;
	}

	public void setLeft_eye(Glass left_eye) {
		this.left_eye = left_eye;
	}


	public Glass getLeft_eye_reading() {
		return left_eye_reading;
	}

	public void setLeft_eye_reading(Glass left_eye_reading) {
		this.left_eye_reading = left_eye_reading;
	}

	public EyeGlasses getEyeglasses() {
		return eyeglasses;
	}

	public void setEyeglasses(EyeGlasses eyeglasses) {
		this.eyeglasses = eyeglasses;
	}

	public EyeGlasses getEyeglasses_reading() {
		return eyeglasses_reading;
	}

	public void setEyeglasses_reading(EyeGlasses eyeglasses_reading) {
		this.eyeglasses_reading = eyeglasses_reading;
	}

	public boolean isEyeglasses_available() {
		return eyeglasses_available;
	}

	public void setEyeglasses_available(boolean eyeglasses_available) {
		this.eyeglasses_available = eyeglasses_available;
	}

	public boolean isReading_glasses_available() {
		return reading_glasses_available;
	}

	public void setReading_glasses_available(boolean reading_glasses_available) {
		this.reading_glasses_available = reading_glasses_available;
	}

	public String getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	
}
