package edu.sabanciuniv.it592.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;

@Stateless
public class UserService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean checkUser(String uname,String pass) {
		TypedQuery<ShopPersonel> query=entityManager.createQuery("select u from ShopPersonel u where u.username=:username and u.password=:password",
				ShopPersonel.class);
		query.setParameter("username", uname);
		query.setParameter("password", pass);
		List<ShopPersonel>users=query.getResultList();

		if(users==null || users.size()<1) {
			return false;
		}
			
		return true;
	}
	
	public boolean registerUser(ShopPersonel user,String shopname) {
		
		try {
			TypedQuery<Shop> query=entityManager.createQuery("select s from Shop s where s.shop_name=:shopname",Shop.class);
			query.setParameter("shopname", shopname);
			Shop shop=query.getSingleResult();
			
			TypedQuery<ShopPersonel> query2=entityManager.createQuery("select u from ShopPersonel u where u.username=:username",ShopPersonel.class);
			query2.setParameter("username", user.getUsername());
			List<ShopPersonel>users=query2.getResultList();
			
			if(shop.getId()!=0) {
				if(users==null || users.size()<1) {
					user.setShop(shop);
					entityManager.persist(user);	
					return true;
				}
			}
			
		}catch (Exception e) {
			return false;
		}	
		
		return false;

	}
	
	public Shop getUserShop(String username) {
		TypedQuery<ShopPersonel> query=entityManager.createQuery("select u from ShopPersonel u where u.username=:username",ShopPersonel.class);
		query.setParameter("username", username);
		ShopPersonel user=query.getSingleResult();
		return user.getShop();
	}
	
	public ShopPersonel getUserByName(String username) {
		TypedQuery<ShopPersonel> query=entityManager.createQuery("select u from ShopPersonel u where u.username=:username",ShopPersonel.class);
		query.setParameter("username", username);
		ShopPersonel user=query.getSingleResult();
		return user;
	}
	
	public boolean checkUserName(String username) {
		TypedQuery<ShopPersonel> query=entityManager.createQuery("select u from ShopPersonel u where u.username=:username",
				ShopPersonel.class);
		query.setParameter("username", username);
		List<ShopPersonel>users=query.getResultList();

		if(users==null || users.size()<1) {
			return true;
		}
			
		return false;
	}
	
	public void updateUser(ShopPersonel user) {
		entityManager.merge(user);
	}
	
}
