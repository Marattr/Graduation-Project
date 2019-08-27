package edu.sabanciuniv.it592.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.sabanciuniv.it592.domain.Category;

@Stateless
public class CategoryService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Category getCategoryByName(String catName) {
		TypedQuery<Category>query=entityManager.createQuery("select c from Category c where c.categoryName=:categoryName",Category.class);
		query.setParameter("categoryName", catName);
		Category category=query.getSingleResult();
		return category;
	}
	
	public void appInitialized() {
		Category cat1=new Category("Glass");
		Category cat2=new Category("Lens");
		Category cat3=new Category("EyeGlasses");
		Category cat4=new Category("SunGlasses");
		
		TypedQuery<Category>query=entityManager.createQuery("select c from Category c",Category.class);
		List<Category>categories=query.getResultList();
		if(categories.size()==0 || categories ==null) {
			entityManager.persist(cat1);
			entityManager.persist(cat2);
			entityManager.persist(cat3);
			entityManager.persist(cat4);			
		}
	
	}
}
