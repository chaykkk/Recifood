package com.ezen.service;

import java.util.List;

import com.ezen.entity.RecipeReply;

public interface RecipeReplyService {
	void insertRecipeReply(RecipeReply recipeReply);
	
	List<RecipeReply> getRecipeReplyList(RecipeReply recipeReply);
	
	int replyCount(long recipe_seq);  
}
