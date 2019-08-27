package edu.sabanciuniv.it592.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.CellEditEvent;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.Customer;
import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.domain.Product;
import edu.sabanciuniv.it592.domain.SunGlasses;
import edu.sabanciuniv.it592.services.AddressService;
import edu.sabanciuniv.it592.services.CustomerService;
import edu.sabanciuniv.it592.services.OrderService;
import edu.sabanciuniv.it592.services.ProductService;

@ManagedBean
@ViewScoped
public class EditSaleBean implements Serializable{
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private AddressService addressService;
	
	@EJB
	private CustomerService customerService;
	
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
	private Address customerAddress;
	private Customer customer;
	private int customerId;
	
	@PostConstruct
	private void loadData() {
		HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.customerId=Integer.valueOf(req.getParameter("customerId"));
		saleInfo();
	}
	
	public void saleInfo() {
				
		customer=customerService.getCustomerById(customerId);	
		customerAddress=addressService.getAddressByCustomerId(customerId);
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
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	try {
        		int tc=Integer.valueOf(sale.getCustomerOrder().getTc_id_number());
        	}catch (Exception e) {
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, " ", "Please enter customer TC ID number again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
			}
        	Customer customer=sale.getCustomerOrder();
        	customerService.updateCustomer(customer);
        	orderService.updateOrder(sale);
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
	}
	
	public void onCellEdit2(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	addressService.updateAddress(customerAddress);
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
        	if(!productService.checkProduct(eyeglasses)) {
        		productService.updateProduct(eyeglasses);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(eyeglasses.getBarcode_number());

        		if(p.getId()==eyeglasses.getId() || p.getId()==0) {
        			productService.updateProduct(eyeglasses);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
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
        	if(!productService.checkProduct(right_eye)) {
        		productService.updateProduct(right_eye);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(right_eye.getBarcode_number());

        		if(p.getId()==right_eye.getId() || p.getId()==0) {
        			productService.updateProduct(right_eye);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit5(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(left_eye)) {
        		productService.updateProduct(left_eye);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(left_eye.getBarcode_number());

        		if(p.getId()==left_eye.getId() || p.getId()==0) {
        			productService.updateProduct(left_eye);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit6(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(eyeglasses_reading)) {
        		productService.updateProduct(eyeglasses_reading);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(eyeglasses_reading.getBarcode_number());

        		if(p.getId()==eyeglasses_reading.getId() || p.getId()==0) {
        			productService.updateProduct(eyeglasses_reading);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit7(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(right_eye_reading)) {
        		productService.updateProduct(right_eye_reading);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(right_eye_reading.getBarcode_number());

        		if(p.getId()==right_eye_reading.getId() || p.getId()==0) {
        			productService.updateProduct(right_eye_reading);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit8(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(left_eye_reading)) {
        		productService.updateProduct(left_eye_reading);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(left_eye_reading.getBarcode_number());

        		if(p.getId()==left_eye_reading.getId() || p.getId()==0) {
        			productService.updateProduct(left_eye_reading);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit9(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(right_eye_lens)) {
        		productService.updateProduct(right_eye_lens);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(right_eye_lens.getBarcode_number());

        		if(p.getId()==right_eye_lens.getId() || p.getId()==0) {
        			productService.updateProduct(right_eye_lens);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit10(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(left_eye_lens)) {
        		productService.updateProduct(left_eye_lens);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(left_eye_lens.getBarcode_number());

        		if(p.getId()==left_eye_lens.getId() || p.getId()==0) {
        			productService.updateProduct(left_eye_lens);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        	} 
        }
	}
	
	public void onCellEdit11(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        String oValue=String.valueOf(oldValue);
        String nValue=String.valueOf(newValue);
         
        if(nValue.length()>0 && !nValue.equals(oValue)) {
        	if(!productService.checkProduct(sunglasses)) {
        		productService.updateProduct(sunglasses);
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);

        	}else {
        		Product p=productService.getProductByBarCode(sunglasses.getBarcode_number());

        		if(p.getId()==sunglasses.getId() || p.getId()==0) {
        			productService.updateProduct(sunglasses);
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}else {
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed!", "Please try different barcode number!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
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

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	

}
