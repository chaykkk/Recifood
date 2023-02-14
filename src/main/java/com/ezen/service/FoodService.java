package com.ezen.service;

import java.util.List;

import com.ezen.entity.Food;

public interface FoodService {
	void insertFood(Food food);
	
	void updateFood(Food food);
	
	void deleteFood(Food food);
	
	List<Food> getFoodList(String username);
	
	Food getFood(Food food);
	
	List<Food> getFoodListUsernameAndCategory(String username, String category);
	
	List<Food> getFoodListUsernameOrderByExpDESC(String username);
	
	List<Food> getFoodListUsernameOrderByExpASC(String username);
	
}
