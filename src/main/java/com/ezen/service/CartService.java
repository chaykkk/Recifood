package com.ezen.service;

import java.util.List;

import com.ezen.entity.Cart;
import com.ezen.entity.CartDetail;

public interface CartService {
	void insertCart(Cart cart);
	
	void updateCart(Cart cart);
	
	void deleteCart(Cart cart);
	
	List<CartDetail> getCartList(String username);
}
