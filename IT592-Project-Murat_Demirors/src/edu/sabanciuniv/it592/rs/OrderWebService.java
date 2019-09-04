package edu.sabanciuniv.it592.rs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Product;
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.OrderService;

@Path("OrderWebService")
@Stateless
public class OrderWebService {
	@EJB
	private OrderService orderService;
	
	@Path("getAllOrders/{shopname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public ResultOrder getAllOrders(@PathParam("shopname")String shopname){
		ResultOrder result=new ResultOrder();
		List<Orders>orders=orderService.getOrdersByShopname(shopname);
		List<OrderProxy>ordersProxy=new ArrayList<>();
		
		for(Orders order:orders) {
			OrderProxy o=new OrderProxy();
						
			String custNameLastname=order.getCustomerOrder().getName()+" "+order.getCustomerOrder().getLastname();
			o.setCustomerNameLastname(custNameLastname);
			o.setCustomerPhone(order.getCustomerOrder().getPhonenumber());
			o.setId(order.getId());
			o.setOrderdate(String.valueOf(order.getOrderdate()));
			o.setPaymentType(order.getPaymentType());
			o.setTotalbill(order.getTotalbill());
			
			String cat=order.getProducts().get(0).getProductCategory().getCategoryName();
			if(cat.equals("Glass") || cat.equals("EyeGlasses")) {
				o.setSaleType("Prescription Sale");
				o.setDate(String.valueOf(order.getDate()));
				o.setDoctorCode(order.getDoctorCode());
				o.setHospital(order.getHospital());
				o.setDoctorName(order.getDoctorName());
				
			}else if(cat.equals("Lens")) {
				o.setSaleType("Lens Sale");
				o.setDate(String.valueOf(order.getDate()));
				o.setDoctorCode(order.getDoctorCode());
				o.setHospital(order.getHospital());
				o.setDoctorName(order.getDoctorName());
				
			}else if(cat.equals("SunGlasses")) {
				o.setSaleType("SunGlasses Sale");
				o.setDoctorCode("0");				
			}
			ordersProxy.add(o);
		}

		if(ordersProxy.size() == 0 || ordersProxy == null) {
			result.setServiceMessageCode(0);
			result.setServiceMessageText("Failed!");
		}else {
			result.setOrders(ordersProxy);
			result.setServiceMessageCode(1);
			result.setServiceMessageText("Success!");
		}
		
		return result;
	}
	
	
	@Path("getAllProductsByOrderId/{orderId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public ResultOrderProducts getAllProductsByOrderId(@PathParam("orderId")int orderId) {
		
		ResultOrderProducts result=new ResultOrderProducts();
		
		List<Product>products=orderService.getOrderProducts(orderId);
		List<Glass>glasses=new ArrayList<>();
		List<Lens>lenses=new ArrayList<>();
		List<EyeGlasses>eyeglasses=new ArrayList<>();
		List<SunGlasses>sunglasses=new ArrayList<>();
		
		for(Product p:products) {
			if(p.getProductCategory().getCategoryName().equals("Glass")) {
				Glass glass=(Glass)p;
				Glass g=new Glass();
				g.setAks(glass.getAks());
				g.setAntirefle(glass.isAntirefle());
				g.setBarcode_number(glass.getBarcode_number());
				g.setBrand(glass.getBrand());
				g.setColor(glass.getColor());
				g.setNumber(glass.getNumber());
				g.setNumber_cyl(glass.getNumber_cyl());
				g.setNumber_cyl_sferik_konkav(glass.isNumber_cyl_sferik_konkav());
				g.setNumber_sferik_konkav(glass.isNumber_sferik_konkav());
				g.setReading_glasses(glass.isReading_glasses());
				g.setRight_eye(glass.isRight_eye());
				g.setType(glass.getType());
				
				glasses.add(g);
				
			}else if(p.getProductCategory().getCategoryName().equals("Lens")) {
				Lens lens=(Lens)p;
				Lens l=new Lens();
				l.setAks(lens.getAks());
				l.setBarcode_number(lens.getBarcode_number());
				l.setBrand(lens.getBrand());
				l.setNumber(lens.getNumber());
				l.setNumber_cyl(lens.getNumber_cyl());
				l.setNumber_cyl_sferik_konkav(lens.isNumber_cyl_sferik_konkav());
				l.setNumber_sferik_konkav(lens.isNumber_sferik_konkav());
				l.setRight_eye(lens.isRight_eye());
				
				lenses.add(l);
				
			}else if(p.getProductCategory().getCategoryName().equals("SunGlasses")) {
				SunGlasses s=(SunGlasses)p;
				SunGlasses s2=new SunGlasses();				
				s2.setBarcode_number(s.getBarcode_number());
				s2.setBrand(s.getBrand());
				s2.setSunglasses_colorcode(s.getSunglasses_colorcode());
				s2.setSunglasses_modelcode(s.getSunglasses_modelcode());
				
				sunglasses.add(s2);
				
			}else if(p.getProductCategory().getCategoryName().equals("EyeGlasses")) {
				EyeGlasses e=(EyeGlasses)p;
				EyeGlasses e2=new EyeGlasses();
				e2.setBarcode_number(e.getBarcode_number());
				e2.setBrand(e.getBrand());
				e2.setReading_glasses(e.isReading_glasses());
				e2.setEyeglasses_colorcode(e.getEyeglasses_colorcode());
				e2.setEyeglasses_modelcode(e.getEyeglasses_modelcode());
				
				eyeglasses.add(e2);
			}
		}
		
		if(eyeglasses.size()==0 && sunglasses.size()==0 && lenses.size()==0 && glasses.size()==0) {
			result.setServiceMessageText("Failed!");
			result.setServiceMessageCode(0);
		}else {
			result.setServiceMessageText("Success!");
			result.setServiceMessageCode(1);
			result.setEyeglasses(eyeglasses);
			result.setGlasses(glasses);
			result.setLenses(lenses);
			result.setSunglasses(sunglasses);
		}				
		
		return result;
	}

}
