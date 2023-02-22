package com.ezen.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.Recipe;

import jakarta.transaction.Transactional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	// 전체 레시피 목록 조회
	@Query("SELECT r FROM Recipe r")
	Page<Recipe> getAllRecipeList(Recipe recipe, Pageable pageable);
	
	// 나의 레시피 목록 조회
	@Query("SELECT r FROM Recipe r WHERE r.member.username=?1")
	Page<Recipe> getRecipeList(String username, Pageable pageable);
	
	// 카테고리별 나의 레시피 목록 조회
	@Query("SELECT r FROM Recipe r WHERE r.member.username=?1 AND r.kind=?2")
	Page<Recipe> getRecipeListByKind(String username, String kind, Pageable pageable);
	
	// 카테고리별 전체 레시피 목록 조회
	@Query("SELECT r FROM Recipe r WHERE r.kind=?1")
	Page<Recipe> getAllRecipeListByKind(String kind, Pageable pegeable);
	
	// 최신순 목록 조회
	@Query("SELECT r FROM Recipe r ORDER BY r.regdate DESC")
	Page<Recipe> getRecipeListDESC(Recipe recipe, Pageable pegeable);
	
	// 인기순 목록 조회
	@Query("SELECT r FROM Recipe r ORDER BY r.good DESC")
	Page<Recipe> getRecipeListGood(Recipe recipe, Pageable pegeable);
	
	// 추천수 높은 상품 3개
	// SELECT * FROM (SELECT * FROM recipe ORDER BY good DESC) WHERE ROWNUM <4;
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM (SELECT * FROM recipe ORDER BY good DESC) WHERE ROWNUM <4", nativeQuery = true)
	List<Recipe> getBestRecipeList(Recipe recipe);
}
