package edu.sabanciuniv.it592.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.Category;
import edu.sabanciuniv.it592.domain.Customer;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.CategoryService;
import edu.sabanciuniv.it592.services.OrderService;
import edu.sabanciuniv.it592.services.ProductService;
import edu.sabanciuniv.it592.services.UserService;

@ManagedBean
public class LensSaleBean {
	
	@EJB
	private CategoryService categoryService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private UserService userService;
	
	@EJB
	private AddressService addressService;
	
	@Inject private LoginBean loginBean;
	
	private Address address=new Address();
	private Customer customer=new Customer();
	private Orders order=new Orders();
	
	private Lens right_eye=new Lens();
	private Lens left_eye=new Lens();
	private UIComponent mybutton;
	private boolean right_eye_storage;
	private boolean left_eye_storage;
	private boolean productsChecked;
	private String totalBill=new String();
	
	
	public String save() {
		
		Category lens=categoryService.getCategoryByName("Lens");	
		
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
		
		right_eye.setProductCategory(lens);
		left_eye.setProductCategory(lens);		
		
		right_eye.setRight_eye(true);
		left_eye.setRight_eye(false);
		
		
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
		
		if((productService.checkProduct(right_eye)) || (productService.checkProduct(left_eye))) {
			FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
					new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode numbers again!"));
			return null;
			
		}else {
			productsChecked=true;												
		}			
	
		
		if(productsChecked) {
			try {
				ShopPersonel personel=userService.getUserByName(loginBean.getUsername());
				order.setPersonelOrder(personel);				
				orderService.saveCustomer(customer);
				orderService.saveOrder(order);														
				orderService.saveProducts(right_eye);
				orderService.saveProducts(left_eye);									
				address.setCustomerAddress(customer);
				addressService.saveAddress(address);			
				
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
		right_eye=new Lens();
		left_eye=new Lens();
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
	public Lens getRight_eye() {
		return right_eye;
	}
	public void setRight_eye(Lens right_eye) {
		this.right_eye = right_eye;
	}
	public Lens getLeft_eye() {
		return left_eye;
	}
	public void setLeft_eye(Lens left_eye) {
		this.left_eye = left_eye;
	}
	public UIComponent getMybutton() {
		return mybutton;
	}
	public void setMybutton(UIComponent mybutton) {
		this.mybutton = mybutton;
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
