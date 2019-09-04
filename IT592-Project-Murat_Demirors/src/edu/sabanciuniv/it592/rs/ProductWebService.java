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
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.ProductService;

@Path("ProductWebService")
@Stateless
public class ProductWebService {
	@EJB
	private ProductService productService;
	
	@Path("getAllProducts/{shopname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public ResultProduct getAllProducts(@PathParam("shopname")String shopname){
		List<Glass>glasses=productService.getGlassesByShopname(shopname);
		List<Lens>lenses=productService.getLensesByShopname(shopname);
		List<EyeGlasses>eyeglasses=productService.getEyeglassesByShopname(shopname);
		List<SunGlasses>sunglasses=productService.getSunglassesByShopname(shopname);
		
		List<Glass>glassesproxy=new ArrayList<>();
		List<Lens>lensesproxy=new ArrayList<>();
		List<EyeGlasses>eyeglassesproxy=new ArrayList<>();
		List<SunGlasses>sunglassesproxy=new ArrayList<>();
		
		ResultProduct result=new ResultProduct();
		
		for(Glass glass:glasses) {
			Glass g=new Glass();
			g.setAddedDate(glass.getAddedDate());
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
			
			glassesproxy.add(g);
		}
		
		for(Lens lens:lenses) {			
			Lens l=new Lens();
			l.setAddedDate(lens.getAddedDate());
			l.setAks(lens.getAks());
			l.setBarcode_number(lens.getBarcode_number());
			l.setBrand(lens.getBrand());
			l.setNumber(lens.getNumber());
			l.setNumber_cyl(lens.getNumber_cyl());
			l.setNumber_cyl_sferik_konkav(lens.isNumber_cyl_sferik_konkav());
			l.setNumber_sferik_konkav(lens.isNumber_sferik_konkav());
			l.setRight_eye(lens.isRight_eye());
			lensesproxy.add(l);			
			
		}
		
		for(EyeGlasses e:eyeglasses) {
			EyeGlasses e2=new EyeGlasses();
			e2.setAddedDate(e.getAddedDate());
			e2.setBarcode_number(e.getBarcode_number());
			e2.setBrand(e.getBrand());
			e2.setReading_glasses(e.isReading_glasses());
			e2.setEyeglasses_colorcode(e.getEyeglasses_colorcode());
			e2.setEyeglasses_modelcode(e.getEyeglasses_modelcode());
			eyeglassesproxy.add(e2);
		}
		
		for(SunGlasses s:sunglasses) {
			SunGlasses s2=new SunGlasses();
			s2.setAddedDate(s.getAddedDate());
			s2.setBarcode_number(s.getBarcode_number());
			s2.setBrand(s.getBrand());
			s2.setSunglasses_colorcode(s.getSunglasses_colorcode());
			s2.setSunglasses_modelcode(s.getSunglasses_modelcode());
			sunglassesproxy.add(s2);
		}
		
		result.setLenses(lensesproxy);
		result.setGlasses(glassesproxy);
		result.setEyeglasses(eyeglassesproxy);
		result.setSunglasses(sunglassesproxy);
		
		if(glassesproxy.size()>0 || lensesproxy.size()>0 || eyeglassesproxy.size()>0 || sunglassesproxy.size()>0) {
			result.setServiceMessageCode(1);
			result.setServiceMessageText("Success!");
		}else {
			result.setServiceMessageCode(0);
			result.setServiceMessageText("Failed!");
		}
		
		
		return result;
	}

}
