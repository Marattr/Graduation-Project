package edu.sabanciuniv.it592.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.ShopService;

@ManagedBean
public class ShopRegisterBean {
	@EJB
	private ShopService shopService;
	
	@EJB
	private AddressService addressService;
	
	private Shop shop=new Shop();
	
	private Address address=new Address();
	
	private UIComponent regbutton;
	
	public String register() {
		if(shopService.checkShop(shop.getShop_name())) {
			FacesMessage msg=new FacesMessage("Registraon failed.Try another shop name!");
			FacesContext.getCurrentInstance().addMessage(regbutton.getClientId(FacesContext.getCurrentInstance()), msg);
			return null;
		
		}else {	
			shopService.registerShop(shop);
			address.setShopAddress(shop);
			addressService.saveAddress(address);			
			FacesMessage msg=new FacesMessage("Registration successfull.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index";
		}
		
	}

	public UIComponent getRegbutton() {
		return regbutton;
	}

	public void setRegbutton(UIComponent regbutton) {
		this.regbutton = regbutton;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
