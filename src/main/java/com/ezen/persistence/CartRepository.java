package com.ezen.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {

}
