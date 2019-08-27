package edu.sabanciuniv.it592.mbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.ShopPersonel;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.UserService;

@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable{
	@EJB
	private UserService userService;
	
	@EJB
	private AddressService addressService;
	
	private ShopPersonel user =new ShopPersonel();
	
	private Address address=new Address();
	
	private String password;
	
	private String shopname;
	
	private boolean registered=false;
	
	private UIComponent mybutton;

	
	public String register() {
		if((user.getPassword().equals(password))) {
			if(userService.registerUser(user, shopname)) {
				address.setShopPersonelAddress(user);
				addressService.saveAddress(address);
				registered=true;		
				FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
						new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Registration succesful, please login!"));
				return null;	
				
			} else {				
				FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()),
						new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed!","Please try another username or shop name!"));
				return null;
				
			}
			
		} else {			
			FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), 
					 new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed!"," Please enter your password again!"));
			return null;
		}
	
				
	}


	public UIComponent getMybutton() {
		return mybutton;
	}


	public void setMybutton(UIComponent mybutton) {
		this.mybutton = mybutton;
	}


	public String getShopname() {
		return shopname;
	}


	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public ShopPersonel getUser() {
		return user;
	}

	public void setUser(ShopPersonel user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
