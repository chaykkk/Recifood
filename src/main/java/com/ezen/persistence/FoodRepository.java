package com.ezen.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ezen.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>, QuerydslPredicateExecutor<Food> {

	@Query("SELECT f FROM Food f WHERE f.member.username=?1")
	Page<Food> getFoodList(String username, Pageable pageable);
	
	// 카테고리별 상품 목록 조회
	@Query("SELECT f FROM Food f WHERE f.member.username=?1 AND f.category=?2")
	Page<Food> getFoodListUsernameAndCategory(String username, String category, Pageable pageable);
	
	// 유통기한 내림차순 목록 조회
	@Query("SELECT f FROM Food f WHERE f.member.username=?1 ORDER BY f.exp DESC")
	Page<Food> getFoodListUsernameOrderByExpDESC(String username, Pageable pageable);
	
	// 유통기한 오름차순 목록 조회
	@Query("SELECT f FROM Food f WHERE f.member.username=?1 ORDER BY f.exp ASC")
	Page<Food> getFoodListUsernameOrderByExpASC(String username, Pageable pageable);
	
	@Query("select f from Food f")
    Page<Food> getFoodList(Pageable pageable);
}
