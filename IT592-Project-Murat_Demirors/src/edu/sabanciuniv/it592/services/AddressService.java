package edu.sabanciuniv.it592.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.sabanciuniv.it592.domain.Address;
import edu.sabanciuniv.it592.domain.Customer;
import edu.sabanciuniv.it592.domain.Shop;
import edu.sabanciuniv.it592.domain.ShopPersonel;

@Stateless
public class AddressService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void saveAddress(Address address) {
		entityManager.persist(address);
	}
	
	public Address getAddressByUserId(int userId) {
		TypedQuery<Address>query=entityManager.createQuery("select a from Address a",Address.class);
		List<Address>addresses=query.getResultList();
		
		for(Address address:addresses) {
			ShopPersonel personel=address.getShopPersonelAddress();
			if(personel!=null) {
				if(personel.getId() == userId) {
					return address;
				}
			}	
		}
		
		return null;
	}
	
	public Address getAddressByShopId(int shopId) {
		TypedQuery<Address>query=entityManager.createQuery("select a from Address a",Address.class);
		List<Address>addresses=query.getResultList();
		
		for(Address address:addresses) {
			Shop shop=address.getShopAddress();
			if(shop!=null) {
				if(shop.getId() == shopId) {
					return address;
				}
			}	
		}
		
		return null;
	}
	
	public Address getAddressByCustomerId(int customerId) {
		TypedQuery<Address>query=entityManager.createQuery("select a from Address a",Address.class);
		List<Address>addresses=query.getResultList();
		
		for(Address address:addresses) {
			Customer customer=address.getCustomerAddress();
			if(customer!=null) {
				if(customer.getId() == customerId) {
					return address;
				}
			}	
		}
		
		return null;
	}
	
	public void updateAddress(Address address) {
		entityManager.merge(address);
	}
}
