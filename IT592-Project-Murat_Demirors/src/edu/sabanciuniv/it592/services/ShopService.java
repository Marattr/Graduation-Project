package edu.sabanciuniv.it592.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.sabanciuniv.it592.domain.Shop;


@Stateless
public class ShopService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean checkShop(String shopName) {
		
		TypedQuery<Shop> query=entityManager.createQuery("select s from Shop s where s.shop_name=:shop_name",Shop.class);
		query.setParameter("shop_name", shopName);
		List<Shop>shops=query.getResultList();
		
		if(shops==null || shops.size()<1) {				
			return false;
		}							
		
		return true;
	}
	
	public void registerShop(Shop shop) {
		entityManager.persist(shop);
	}
	
	public void updateShop(Shop shop) {
		entityManager.merge(shop);
	}
	
	public Shop getShopByName(String shop_name) {
		TypedQuery<Shop> query=entityManager.createQuery("select s from Shop s where s.shop_name=:shop_name",Shop.class);
		query.setParameter("shop_name", shop_name);
		Shop shop=query.getSingleResult();
		return shop;
	}

}
