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
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.CategoryService;
import edu.sabanciuniv.it592.services.OrderService;
import edu.sabanciuniv.it592.services.ProductService;
import edu.sabanciuniv.it592.services.UserService;

@ManagedBean
public class SunGlassesSaleBean {
	
	@EJB
	private UserService userService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private CategoryService categoryService;
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private AddressService addressService;
	
	@Inject private LoginBean loginBean;
	
	
	private Address address=new Address();
	private SunGlasses sunglasses=new SunGlasses();	
	private Orders order=new Orders();	
	private Customer customer=new Customer();	
	private UIComponent mybutton;	
	private String totalBill=new String();	
	private boolean productsChecked=false;	
	private boolean sunglasses_storage;
	
	
	public String save() {
		Category sunglassesCat=categoryService.getCategoryByName("SunGlasses");	
		
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
		
		sunglasses.setProductCategory(sunglassesCat);						
		sunglasses.setProductOrder(order);
		sunglasses.setShopProduct(shop);	
		
		if(sunglasses_storage) {
			
			productService.removeProductFromStorage(loginBean.getShopname(),sunglasses);
		}	
		
		if((productService.checkProduct(sunglasses))) {
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
				orderService.saveProducts(sunglasses);	
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
		sunglasses=new SunGlasses();	
		order=new Orders();	
		customer=new Customer();
	}
	
	public SunGlasses getSunglasses() {
		return sunglasses;
	}

	public void setSunglasses(SunGlasses sunglasses) {
		this.sunglasses = sunglasses;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public UIComponent getMybutton() {
		return mybutton;
	}

	public void setMybutton(UIComponent mybutton) {
		this.mybutton = mybutton;
	}

	public String getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

	public boolean isSunglasses_storage() {
		return sunglasses_storage;
	}

	public void setSunglasses_storage(boolean sunglasses_storage) {
		this.sunglasses_storage = sunglasses_storage;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
