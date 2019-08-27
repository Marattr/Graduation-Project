package edu.sabanciuniv.it592.mbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.CellEditEvent;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.ShopService;
import edu.sabanciuniv.it592.services.UserService;

@ManagedBean
@ViewScoped
public class EditShopUserBean implements Serializable{
	@EJB
	private ShopService shopService;
	
	@EJB
	private UserService userService;
	
	@EJB
	private AddressService addressService;
	
	@Inject private LoginBean loginBean;
		
	private Shop shop;
	private ShopPersonel personel;
	private Address shopAddress;
	private Address personelAddress;
	
	@PostConstruct
	public void loadData() {
		personel=userService.getUserByName(loginBean.getUsername());
		personelAddress=addressService.getAddressByUserId(personel.getId());
		shop=shopService.getShopByName(loginBean.getShopname());
		shopAddress=addressService.getAddressByShopId(shop.getId());				
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);

        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(userService.checkUserName(personel.getUsername())) {
        		userService.updateUser(personel);
        		
        		HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        		String contextPath=req.getContextPath();
                FacesContext context = FacesContext.getCurrentInstance();
                FacesContext.getCurrentInstance().addMessage(loginBean.getLoginbutton().getClientId(FacesContext.getCurrentInstance()), 
                		new FacesMessage(FacesMessage.SEVERITY_INFO," ","User updated! Please login again!"));
                ExternalContext externalContext = context.getExternalContext();
                externalContext.getFlash().setKeepMessages(true);
                externalContext.invalidateSession();
                try {
					externalContext.redirect(contextPath+"/login.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else {
        		ShopPersonel user=userService.getUserByName(personel.getUsername());

        		if(user.getId()==personel.getId() || user.getId()==0) {
        			userService.updateUser(personel);
        			HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            		String contextPath=req.getContextPath();
                    FacesContext context = FacesContext.getCurrentInstance();
                    FacesContext.getCurrentInstance().addMessage(loginBean.getLoginbutton().getClientId(FacesContext.getCurrentInstance()), 
                    		new FacesMessage(FacesMessage.SEVERITY_INFO," ","User updated! Please login again!"));
                    ExternalContext externalContext = context.getExternalContext();
                    externalContext.getFlash().setKeepMessages(true);
                    externalContext.invalidateSession();
                    try {
    					externalContext.redirect(contextPath+"/login.xhtml");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different username!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	}            
        }
	}
	
	public void onCellEdit2(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	addressService.updateAddress(personelAddress);
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
	}
	
	public void onCellEdit3(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);

        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!shopService.checkShop(shop.getShop_name())) {
        		shopService.updateShop(shop);
        		
        		HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        		String contextPath=req.getContextPath();
                FacesContext context = FacesContext.getCurrentInstance();
                FacesContext.getCurrentInstance().addMessage(loginBean.getLoginbutton().getClientId(FacesContext.getCurrentInstance()), 
                		new FacesMessage(FacesMessage.SEVERITY_INFO," ","Shop updated! Please login again!"));
                ExternalContext externalContext = context.getExternalContext();
                externalContext.getFlash().setKeepMessages(true);
                externalContext.invalidateSession();
                try {
					externalContext.redirect(contextPath+"/login.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else {
        		Shop s=shopService.getShopByName(shop.getShop_name());

        		if(s.getId()==shop.getId() || s.getId()==0) {
        			shopService.updateShop(shop);
        			HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            		String contextPath=req.getContextPath();
                    FacesContext context = FacesContext.getCurrentInstance();
                    FacesContext.getCurrentInstance().addMessage(loginBean.getLoginbutton().getClientId(FacesContext.getCurrentInstance()), 
                    		new FacesMessage(FacesMessage.SEVERITY_INFO," ","Shop updated! Please login again!"));
                    ExternalContext externalContext = context.getExternalContext();
                    externalContext.getFlash().setKeepMessages(true);
                    externalContext.invalidateSession();
                    try {
    					externalContext.redirect(contextPath+"/login.xhtml");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different shop name!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	}            
        }
	}
	
	public void onCellEdit4(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	addressService.updateAddress(shopAddress);
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public ShopPersonel getPersonel() {
		return personel;
	}

	public void setPersonel(ShopPersonel personel) {
		this.personel = personel;
	}

	public Address getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(Address shopAddress) {
		this.shopAddress = shopAddress;
	}

	public Address getPersonelAddress() {
		return personelAddress;
	}

	public void setPersonelAddress(Address personelAddress) {
		this.personelAddress = personelAddress;
	}
	

}
