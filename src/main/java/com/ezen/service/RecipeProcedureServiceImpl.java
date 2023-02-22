package com.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.entity.RecipeProcedure;
import com.ezen.persistence.RecipeProcedureRepository;

@Service
public class RecipeProcedureServiceImpl implements RecipeProcedureService {

	@Autowired
	private RecipeProcedureRepository recipeProcedureRepo;
	
	@Override
	public void insertRecipeProcedure(RecipeProcedure recipeProcedure) {
		recipeProcedureRepo.save(recipeProcedure);
	}

	@Override
	public void updateRecipeProcedure(RecipeProcedure recipeProcedure) {
		RecipeProcedure findRecipePro = recipeProcedureRepo.findById(recipeProcedure.getRecipepro_seq()).get();
		findRecipePro.setProcedure(recipeProcedure.getProcedure());
		findRecipePro.setFilename(recipeProcedure.getFilename());
		findRecipePro.setFilepath(recipeProcedure.getFilepath());
		
		recipeProcedureRepo.save(findRecipePro);
	}

	@Override
	public void deleteRecipeProcedure(long recipe_seq) {
		
		recipeProcedureRepo.deleteByRecipeSeq(recipe_seq);
	}

	@Override
	public List<RecipeProcedure> getRecipeProcedureList(long recipe_seq) {
		return recipeProcedureRepo.getRecipeProcedureList(recipe_seq);
	}

	@Override
	public RecipeProcedure getRecipeProcedure(long recipe_seq) {
		return recipeProcedureRepo.getRecipeProcedure(recipe_seq);
	}

}
