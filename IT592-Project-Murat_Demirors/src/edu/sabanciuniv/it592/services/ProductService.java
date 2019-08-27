package edu.sabanciuniv.it592.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.Product;
import edu.sabanciuniv.it592.domain.SunGlasses;

@Stateless
public class ProductService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product getProductById(int id) {
		return entityManager.find(Product.class, id);
	}
	
	public boolean saveProduct(Product product) {
		TypedQuery<Product>query=entityManager.createQuery("select p from Product p",Product.class);		
		List<Product> products=query.getResultList();
		for(Product p:products) {
			if(p.getBarcode_number().equals(product.getBarcode_number())) {
				return false;
			}
		}		
		entityManager.persist(product);
		return true;
	}
	
	public Product getProductByBarCode(String barcode) {
		TypedQuery<Product>query=entityManager.createQuery("select p from Product p where p.barcode_number=:barcode_number",Product.class);
		query.setParameter("barcode_number", barcode);
		Product product=query.getSingleResult();
		return product;
	}
	
	public boolean checkProduct(Product product) {
		TypedQuery<Product>query=entityManager.createQuery("select p from Product p where p.barcode_number=:barcode_number",Product.class);
		query.setParameter("barcode_number", product.getBarcode_number());
		List<Product> products=query.getResultList();
		if(products==null || products.size()<1) {
			return false;
		}			
		return true;			
	}
	
	public List<Glass>getGlassesByShopname(String shopname){
		
		TypedQuery<Glass>query=entityManager.createQuery("select g from Glass g",Glass.class);
		List<Glass> glasses=query.getResultList();
		List<Glass> result=new ArrayList<>();
		
		
		for(Glass g:glasses) {
			if((g.getShopProduct().getShop_name().equals(shopname))) {	
				if(g.getProductOrder() == null) {
					result.add(g);
				}							
			}
		}
		return result;
	}
	
	public List<EyeGlasses>getEyeglassesByShopname(String shopname){
		

		TypedQuery<EyeGlasses>query=entityManager.createQuery("select e from EyeGlasses e",EyeGlasses.class);
		List<EyeGlasses> eyeglasses=query.getResultList();
		List<EyeGlasses> result=new ArrayList<>();
		
		
		for(EyeGlasses e:eyeglasses) {
			if((e.getShopProduct().getShop_name().equals(shopname))) {	
				if(e.getProductOrder()== null) {
					result.add(e);
				}							
			}
		}
		return result;
	}
	
	public List<SunGlasses>getSunglassesByShopname(String shopname){
		

		TypedQuery<SunGlasses>query=entityManager.createQuery("select s from SunGlasses s",SunGlasses.class);
		List<SunGlasses> sunglasses=query.getResultList();
		List<SunGlasses> result=new ArrayList<>();
		
		
		for(SunGlasses s:sunglasses) {
			if((s.getShopProduct().getShop_name().equals(shopname))) {	
				if(s.getProductOrder() == null)
				result.add(s);				
			}
		}
		return result;
	}
	
	public List<Lens>getLensesByShopname(String shopname){
		

		TypedQuery<Lens>query=entityManager.createQuery("select l from Lens l",Lens.class);
		List<Lens> lenses=query.getResultList();
		List<Lens> result=new ArrayList<>();
		
		
		for(Lens l:lenses) {
			if((l.getShopProduct().getShop_name().equals(shopname))) {	
				if(l.getProductOrder()==null) {
					result.add(l);
				}						
			}
		}
		return result;
	}
	
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}
	
	public void deleteProduct(Product product) {
		entityManager.remove(product);
	}
	
	public void removeProductFromStorage(String shopname,Product product) {
		
		TypedQuery<Product>query=entityManager.createQuery("select p from Product p",Product.class);
		List<Product>products=query.getResultList();
		for(Product p:products) {
			
			if(p.getProductOrder()==null) {
				
				if(p.getShopProduct().getShop_name().equals(shopname)) {
					
					if(p.getBarcode_number().equals(product.getBarcode_number())) {						
						
						entityManager.remove(p);
					}				
				}
			}
		}
	}
}
