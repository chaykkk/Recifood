package com.ezen.service;

import java.util.List;

import com.ezen.entity.RecipeProcedure;

public interface RecipeProcedureService {
	void insertRecipeProcedure(RecipeProcedure recipeProcedure);
	
	void updateRecipeProcedure(RecipeProcedure recipeProcedure);
	
	void deleteRecipeProcedure(long recipe_seq);
	
	List<RecipeProcedure> getRecipeProcedureList(long recipe_seq);
	
	RecipeProcedure getRecipeProcedure(long recipe_seq);
}
