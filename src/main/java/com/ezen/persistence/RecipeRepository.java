package com.ezen.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.Recipe;

import jakarta.transaction.Transactional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	@Query("SELECT r FROM Recipe r WHERE r.member.username=?1")
	List<Recipe> getRecipeList(String username);
	
	// 카테고리별 나의 레시피 목록 조회
	@Query("SELECT r FROM Recipe r WHERE r.member.username=?1 AND r.kind=?2")
	List<Recipe> getRecipeListByKind(String username, String kind);
	
	// 카테고리별 전체 레시피 목록 조회
	@Query("SELECT r FROM Recipe r WHERE r.kind=?1")
	List<Recipe> getAllRecipeListByKind(String kind);
	
	// 최신순 목록 조회
	@Query("SELECT r FROM Recipe r ORDER BY r.regdate DESC")
	List<Recipe> getRecipeListDESC(Recipe recipe);
	
	// 인기순 목록 조회
	@Query("SELECT r FROM Recipe r ORDER BY r.good DESC")
	List<Recipe> getRecipeListGood(Recipe recipe);
	
	// 추천수 높은 상품 3개
	// SELECT * FROM (SELECT * FROM recipe ORDER BY good DESC) WHERE ROWNUM <4;
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM (SELECT * FROM recipe ORDER BY good DESC) WHERE ROWNUM <4", nativeQuery = true)
	List<Recipe> getBestRecipeList(Recipe recipe);
}
