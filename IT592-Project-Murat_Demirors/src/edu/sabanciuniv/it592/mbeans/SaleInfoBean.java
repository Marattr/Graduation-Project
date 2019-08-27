package edu.sabanciuniv.it592.mbeans;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Product;
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.OrderService;

@ManagedBean
@ViewScoped
public class SaleInfoBean implements Serializable{
	@EJB
	private OrderService orderService;
	
	@Inject private LoginBean loginBean;
	
	private Orders sale;
	
	private Glass right_eye;
	private Glass left_eye;
	private Glass right_eye_reading;
	private Glass left_eye_reading;
	private EyeGlasses eyeglasses;
	private EyeGlasses eyeglasses_reading;
	private Lens right_eye_lens;
	private Lens left_eye_lens;
	private SunGlasses sunglasses;
	private int customerId;
	
	@PostConstruct
	public void init() {
		HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.customerId=Integer.valueOf(req.getParameter("customerId"));
		saleInfo();		
	}
	

	public void saleInfo() {
						
		String shopname=loginBean.getShopname();
		
		sale=orderService.getOrderInfo(shopname, customerId);
		
		List<Product> products=orderService.getOrderProducts(sale.getId());
		
		for(Product product:products) {
			
			if(product.getProductCategory().getCategoryName().equals("Glass")) {
				
				Glass glass=(Glass)product;
				
				if(glass.isRight_eye() && !glass.isReading_glasses()) {
					right_eye=glass;
				}else if(!glass.isRight_eye() && !glass.isReading_glasses()) {
					left_eye=glass;
				}else if(glass.isRight_eye() && glass.isReading_glasses()) {
					right_eye_reading=glass;
				}else if(!glass.isRight_eye() && glass.isReading_glasses()) {
					left_eye_reading=glass;
				}
				
			}else if(product.getProductCategory().getCategoryName().equals("EyeGlasses")) {
				
				EyeGlasses eyeGlasses=(EyeGlasses)product;
				if(!eyeGlasses.isReading_glasses()) {
					eyeglasses=eyeGlasses;
				}else if(eyeGlasses.isReading_glasses()) {
					eyeglasses_reading=eyeGlasses;
				}
				
			}else if(product.getProductCategory().getCategoryName().equals("Lens")) {
				Lens lens=(Lens)product;
				if(lens.isRight_eye()) {
					right_eye_lens=lens;
				}else if(!lens.isRight_eye()) {
					left_eye_lens=lens;
				}
			}else if(product.getProductCategory().getCategoryName().equals("SunGlasses")) {
				sunglasses=(SunGlasses)product;	
			}
		}
		
	}

	public Orders getSale() {
		return sale;
	}

	public void setSale(Orders sale) {
		this.sale = sale;
	}

	public Glass getRight_eye() {
		return right_eye;
	}

	public void setRight_eye(Glass right_eye) {
		this.right_eye = right_eye;
	}

	public Glass getLeft_eye() {
		return left_eye;
	}

	public void setLeft_eye(Glass left_eye) {
		this.left_eye = left_eye;
	}

	public Glass getRight_eye_reading() {
		return right_eye_reading;
	}

	public void setRight_eye_reading(Glass right_eye_reading) {
		this.right_eye_reading = right_eye_reading;
	}

	public Glass getLeft_eye_reading() {
		return left_eye_reading;
	}

	public void setLeft_eye_reading(Glass left_eye_reading) {
		this.left_eye_reading = left_eye_reading;
	}

	public EyeGlasses getEyeglasses() {
		return eyeglasses;
	}

	public void setEyeglasses(EyeGlasses eyeglasses) {
		this.eyeglasses = eyeglasses;
	}

	public EyeGlasses getEyeglasses_reading() {
		return eyeglasses_reading;
	}

	public void setEyeglasses_reading(EyeGlasses eyeglasses_reading) {
		this.eyeglasses_reading = eyeglasses_reading;
	}

	public Lens getRight_eye_lens() {
		return right_eye_lens;
	}

	public void setRight_eye_lens(Lens right_eye_lens) {
		this.right_eye_lens = right_eye_lens;
	}

	public Lens getLeft_eye_lens() {
		return left_eye_lens;
	}

	public void setLeft_eye_lens(Lens left_eye_lens) {
		this.left_eye_lens = left_eye_lens;
	}

	public SunGlasses getSunglasses() {
		return sunglasses;
	}

	public void setSunglasses(SunGlasses sunglasses) {
		this.sunglasses = sunglasses;
	}
	
	
	
}
