package edu.sabanciuniv.it592.mbeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.sabanciuniv.it592.domain.Category;
import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.CategoryService;
import edu.sabanciuniv.it592.services.ProductService;
import edu.sabanciuniv.it592.services.ShopService;
import edu.sabanciuniv.it592.services.UserService;


@ManagedBean
@ViewScoped
public class AddProductBean implements Serializable{
	@EJB
	private ProductService productService;
	
	@EJB
	private ShopService shopService;
	
	@EJB
	private CategoryService categoryService;
	
	@EJB
	private UserService userService;
	
	@Inject private LoginBean loginBean;
	
	private Glass glass=new Glass();
	private Lens lens=new Lens();	
	private EyeGlasses eyeglasses=new EyeGlasses();
	private SunGlasses sunglasses=new SunGlasses();
	
	public void reloadData(){
		glass=new Glass();
		lens=new Lens();
		eyeglasses=new EyeGlasses();
		sunglasses=new SunGlasses();
	}
	
	public String saveGlass() {
		Category glassCat=categoryService.getCategoryByName("Glass");						
		String shopname=loginBean.getShopname();
		Shop shop=shopService.getShopByName(shopname);		
		ShopPersonel personel=userService.getUserByName(loginBean.getUsername());
		Date now=new Date();
		glass.setAddedDate(now);
		glass.setProductPersonel(personel);
		glass.setShopProduct(shop);
		glass.setProductCategory(glassCat);	
		if(glass.getBarcode_number().length()==0 || glass.getBrand().length()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please fill the required areas!"));
			return null;
		}
		if(productService.saveProduct(glass)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Product Added!"));
			reloadData();
			return null;
		}		
			
		reloadData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode code or try another one!"));
		return null;
	}
	
	public String saveLens() {		
		Category lensCat=categoryService.getCategoryByName("Lens");
		String shopname=loginBean.getShopname();
		Shop shop=shopService.getShopByName(shopname);
		ShopPersonel personel=userService.getUserByName(loginBean.getUsername());
		Date now=new Date();
		lens.setAddedDate(now);
		lens.setProductPersonel(personel);
		lens.setShopProduct(shop);
		lens.setProductCategory(lensCat);
		if(lens.getBarcode_number().length()==0 || lens.getBrand().length()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please fill the required areas!"));
			return null;
		}
		if(productService.saveProduct(lens)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Product Added!"));
			reloadData();
			return null;
		}
		reloadData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode code or try another one!"));
		return null;
	}
	
	public String saveEyeglasses() {
		Category eyeglassesCat=categoryService.getCategoryByName("EyeGlasses");
		String shopname=loginBean.getShopname();
		Shop shop=shopService.getShopByName(shopname);
		ShopPersonel personel=userService.getUserByName(loginBean.getUsername());
		Date now=new Date();
		eyeglasses.setAddedDate(now);
		eyeglasses.setProductPersonel(personel);
		eyeglasses.setShopProduct(shop);
		eyeglasses.setProductCategory(eyeglassesCat);
		if(eyeglasses.getBarcode_number().length()==0 || eyeglasses.getBrand().length()==0 || eyeglasses.getEyeglasses_colorcode().length()==0 || 
				eyeglasses.getEyeglasses_modelcode().length()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please fill the required areas!"));
			return null;
		}
		if(productService.saveProduct(eyeglasses)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Product Added!"));			
			reloadData();
			return null;
		}
		reloadData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode code or try another one!"));
		return null;
	}
	
	public String saveSunGlasses() {
		Category sunglassesCat=categoryService.getCategoryByName("SunGlasses");
		String shopname=loginBean.getShopname();
		Shop shop=shopService.getShopByName(shopname);
		ShopPersonel personel=userService.getUserByName(loginBean.getUsername());
		Date now=new Date();
		sunglasses.setAddedDate(now);
		sunglasses.setProductPersonel(personel);
		sunglasses.setProductCategory(sunglassesCat);
		sunglasses.setShopProduct(shop);
		if(sunglasses.getBarcode_number().length()==0 || sunglasses.getBrand().length()==0 || sunglasses.getSunglasses_colorcode().length()==0 || 
				sunglasses.getSunglasses_modelcode().length()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please fill the required areas!"));
			return null;
		}
		if(productService.saveProduct(sunglasses)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Product Added!"));
			reloadData();
			return null;
		}
		reloadData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Failed", "Please enter barcode code or try another one!"));
		return null;
	}

	public Glass getGlass() {
		return glass;
	}

	public void setGlass(Glass glass) {
		this.glass = glass;
	}

	public Lens getLens() {
		return lens;
	}

	public void setLens(Lens lens) {
		this.lens = lens;
	}

	public EyeGlasses getEyeglasses() {
		return eyeglasses;
	}

	public void setEyeglasses(EyeGlasses eyeglasses) {
		this.eyeglasses = eyeglasses;
	}

	public SunGlasses getSunglasses() {
		return sunglasses;
	}

	public void setSunglasses(SunGlasses sunglasses) {
		this.sunglasses = sunglasses;
	}
	
	
	
	
}
