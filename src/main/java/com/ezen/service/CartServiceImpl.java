package com.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.entity.Cart;
import com.ezen.entity.CartDetail;
import com.ezen.persistence.CartRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertCart(Cart cart) {
		cartRepo.save(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		Cart findCart = cartRepo.findById(cart.getCart_seq()).get();
		findCart.setQuantity(cart.getQuantity());
		findCart.setTotalprice(cart.getTotalprice());
		
		cartRepo.save(findCart);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepo.deleteById(cart.getCart_seq());
	}

	@Override
	public List<CartDetail> getCartList(String username) {
		
		String sql =
		  "SELECT 1 AS cd_seq, fd.funding_seq funding_seq, fd.filename filename, fd.funding_name funding_name, fd.price price, " +
		  "c.cart_seq cart_seq, c.quantity quantity, c.username username, c.regdate regdate, c.totalprice " + 
		  "FROM Funding fd, Cart c " +
		  "WHERE fd.funding_seq=c.funding_seq AND c.username=?";

		Query query = entityManager.createNativeQuery(sql, "CartListResults");
		query.setParameter(1, username);
		
		@SuppressWarnings("unchecked")
		List<CartDetail> cartlist = query.getResultList();
		
		return cartlist;
	}

}
