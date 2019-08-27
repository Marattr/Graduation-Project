package edu.sabanciuniv.it592.rs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.services.UserService;

@Path("UserWebService")
@Stateless
public class UserWebService {
	
	@EJB
	private UserService userService;
	
	
	@Path("checkUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultUser getAllProducts(PersonelProxy personel){
		ResultUser result=new ResultUser();
		if(userService.checkUser(personel.getUsername(),personel.getPassword())) {
			Shop shop=userService.getUserShop(personel.getUsername());
			result.setServiceMessageCode(1);
			result.setServiceMessageText("Success!");
			result.setShopName(shop.getShop_name());			
		}else {
			result.setServiceMessageCode(0);
			result.setServiceMessageText("Failed!");
		}
		
		return result;
	}
}
