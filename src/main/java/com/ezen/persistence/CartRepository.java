package com.ezen.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
