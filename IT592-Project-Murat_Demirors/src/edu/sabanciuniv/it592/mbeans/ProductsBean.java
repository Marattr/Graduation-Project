package edu.sabanciuniv.it592.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import edu.sabanciuniv.it592.domain.Category;
import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Product;
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.ProductService;

@ViewScoped
@ManagedBean
public class ProductsBean implements Serializable{
	@EJB
	private ProductService productService;
	
	@Inject private LoginBean loginBean;
	
	private List<Glass>glasses=new ArrayList<>();
	private List<Lens>lenses=new ArrayList<>();
	private List<EyeGlasses>eyeglasses=new ArrayList<>();
	private List<SunGlasses>sunglasses=new ArrayList<>();
	private int productId;
	
	@PostConstruct
	public void getData() {
				
		glasses=productService.getGlassesByShopname(loginBean.getShopname());				
		eyeglasses=productService.getEyeglassesByShopname(loginBean.getShopname());				
		sunglasses=productService.getSunglassesByShopname(loginBean.getShopname());			
		lenses=productService.getLensesByShopname(loginBean.getShopname());							
	}
	
	public void setIds(int productId) {
		this.productId=productId;
	}
	
	public String removeProduct() {
		Product product=productService.getProductById(productId);
		productService.removeProductFromStorage(loginBean.getShopname(), product);
		getData();
		return null;
	}
	
	public List<Glass> getGlasses() {
		return glasses;
	}
	public void setGlasses(List<Glass> glasses) {
		this.glasses = glasses;
	}
	public List<Lens> getLenses() {
		return lenses;
	}
	public void setLenses(List<Lens> lenses) {
		this.lenses = lenses;
	}
	public List<EyeGlasses> getEyeglasses() {
		return eyeglasses;
	}
	public void setEyeglasses(List<EyeGlasses> eyeglasses) {
		this.eyeglasses = eyeglasses;
	}
	public List<SunGlasses> getSunglasses() {
		return sunglasses;
	}
	public void setSunglasses(List<SunGlasses> sunglasses) {
		this.sunglasses = sunglasses;
	}
	
	
	
}
