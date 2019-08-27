package edu.sabanciuniv.it592.mbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.services.UserService;

@Named
@SessionScoped
public class LoginBean implements Serializable{
	@EJB
	private UserService userService;
	
	private String username;
	private String password;
	private String shopname;
	private boolean loggedin=false;
	private UIComponent loginbutton;
	
	public String login() {
		
		if(userService.checkUser(username, password)) {
			Shop shop=userService.getUserShop(username);
			shopname=shop.getShop_name();
			this.loggedin=true;
			return "login/home";
		} else {
			FacesContext.getCurrentInstance().addMessage(loginbutton.getClientId(FacesContext.getCurrentInstance()), 
					new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Wrong credentials! If you are not registered. Please register!"));
			return null;
		}

	}
	
	public void logout() {
		
		HttpServletResponse resp=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();		
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		try {
			username = null;
			password=null;
			loggedin = false;
			resp.sendRedirect(contextPath + "/index.xhtml");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	

	public UIComponent getLoginbutton() {
		return loginbutton;
	}

	public void setLoginbutton(UIComponent loginbutton) {
		this.loginbutton = loginbutton;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
	

}
