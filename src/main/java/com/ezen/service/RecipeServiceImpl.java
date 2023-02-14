package com.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.entity.Recipe;
import com.ezen.persistence.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;
	
	@Override
	public void insertRecipe(Recipe recipe) {
    	recipeRepo.save(recipe);
	}

	@Override
	public void updateRecipe(Recipe recipe) {
		Recipe findRecipe = recipeRepo.findById(recipe.getRecipe_seq()).get();
		findRecipe.setKind(recipe.getKind());
		findRecipe.setRecipe_name(recipe.getRecipe_name());
		findRecipe.setContent(recipe.getContent());
		findRecipe.setIngredient(recipe.getIngredient());
		findRecipe.setCooking_time(recipe.getCooking_time());
		findRecipe.setAmount(recipe.getAmount());
		findRecipe.setDegree(recipe.getDegree());
		findRecipe.setProcedure(recipe.getProcedure());
		findRecipe.setFilename(recipe.getFilename());
		findRecipe.setFilepath(recipe.getFilepath());
		
		recipeRepo.save(findRecipe);
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		recipeRepo.deleteById(recipe.getRecipe_seq());
	}

	@Override
	public List<Recipe> getRecipeList(String username) {
		return (List<Recipe>) recipeRepo.getRecipeList(username);
	}

	@Override
	public List<Recipe> getRecipeListByKind(String username, String kind) {
		return (List<Recipe>) recipeRepo.getRecipeListByKind(username, kind);
	}

	@Override
	public Recipe getRecipe(Recipe recipe) {
		return recipeRepo.findById(recipe.getRecipe_seq()).get();
	}

	@Override
	public List<Recipe> getAllRecipeList(Recipe recipe) {
		return recipeRepo.findAll();
	}

	@Override
	public List<Recipe> getAllRecipeListByKind(String kind) {
		return recipeRepo.getAllRecipeListByKind(kind);
	}

	@Override
	public List<Recipe> getRecipeListDESC(Recipe recipe) {
		return recipeRepo.getRecipeListDESC(recipe);
	}

	@Override
	public List<Recipe> getRecipeListGood(Recipe recipe) {
		return recipeRepo.getRecipeListGood(recipe);
	}

	@Override
	public List<Recipe> getBestRecipeList(Recipe recipe) {
		return recipeRepo.getBestRecipeList(recipe);
	}

}
