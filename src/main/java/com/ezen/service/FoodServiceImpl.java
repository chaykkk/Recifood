package com.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.entity.Food;
import com.ezen.persistence.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodRepository foodRepo;
	
	@Override
	public void insertFood(Food food) {
		foodRepo.save(food);
	}

	@Override
	public void updateFood(Food food) {
		Food findFood = foodRepo.findById(food.getFood_seq()).get();
		findFood.setCategory(food.getCategory());
		findFood.setName(food.getName());
		findFood.setExp(food.getExp());
		
		foodRepo.save(food);
	}

	@Override
	public void deleteFood(Food food) {
		foodRepo.deleteById(food.getFood_seq());
		
	}

	@Override
	public List<Food> getFoodList(String username) {
		return foodRepo.getFoodList(username);
	}

	@Override
	public Food getFood(Food food) {
		return foodRepo.findById(food.getFood_seq()).get();
	}
	
	@Override
	public List<Food> getFoodListUsernameAndCategory(String username, String category) {
		return foodRepo.getFoodListUsernameAndCategory(username, category);
	}

	
	@Override
	public List<Food> getFoodListUsernameOrderByExpDESC(String username) {
		return foodRepo.getFoodListUsernameOrderByExpDESC(username);
	}

	@Override
	public List<Food> getFoodListUsernameOrderByExpASC(String username) {
		return foodRepo.getFoodListUsernameOrderByExpASC(username);
	}
	
}
