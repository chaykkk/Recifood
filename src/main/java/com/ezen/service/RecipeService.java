package com.ezen.service;

import java.util.List;

import com.ezen.entity.Recipe;

public interface RecipeService {
	void insertRecipe(Recipe recipe);
	
	void updateRecipe(Recipe recipe);
	
	void deleteRecipe(Recipe recipe);
	
	List<Recipe> getRecipeList(String username);
	
	// 카테고리별 나의 레시피 목록 조회
	List<Recipe> getRecipeListByKind(String username, String kind);
	
	Recipe getRecipe(Recipe recipe);
	
	// 전체 레시피 조회
	List<Recipe> getAllRecipeList(Recipe recipe);
	
	// 카테고리별 전체 레시피 목록 조회
	List<Recipe> getAllRecipeListByKind(String kind);
	
	// 최신순 목록 조회
	List<Recipe> getRecipeListDESC(Recipe recipe);
	
	// 인기순 목록 조회
	List<Recipe> getRecipeListGood(Recipe recipe);
	
	List<Recipe> getBestRecipeList(Recipe recipe);
}
