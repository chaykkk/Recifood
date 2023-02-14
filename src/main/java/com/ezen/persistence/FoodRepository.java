package com.ezen.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

	@Query("SELECT f FROM Food f WHERE f.member.username=?1")
	List<Food> getFoodList(String username);
	
	// 카테고리별 상품 목록 조회
	@Query("SELECT f FROM Food f WHERE f.member.username=?1 AND f.category=?2")
	List<Food> getFoodListUsernameAndCategory(String username, String category);
	
	// 유통기한 내림차순 목록 조회
	@Query("SELECT f FROM Food f WHERE f.member.username=?1 ORDER BY f.exp DESC")
	List<Food> getFoodListUsernameOrderByExpDESC(String username);
	
	// 유통기한 오름차순 목록 조회
	@Query("SELECT f FROM Food f WHERE f.member.username=?1 ORDER BY f.exp ASC")
	List<Food> getFoodListUsernameOrderByExpASC(String username);
	
}
