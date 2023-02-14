package com.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.entity.RecipeReply;
import com.ezen.persistence.RecipeReplyRepository;

@Service
public class RecipeReplyServiceImpl implements RecipeReplyService {

	@Autowired
	private RecipeReplyRepository recipeReplyRepo;
	
	@Override
	public void insertRecipeReply(RecipeReply recipeReply) {
		recipeReplyRepo.save(recipeReply);
	}

	@Override
	public List<RecipeReply> getRecipeReplyList(RecipeReply recipeReply) {
		return recipeReplyRepo.findRecipeReplyByRecipeSeq(recipeReply.getRecipe().getRecipe_seq());
	}

	@Override
	public int replyCount(long recipe_seq) {
		return recipeReplyRepo.replyCount(recipe_seq);
	}

}
