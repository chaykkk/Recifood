package com.ezen.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.RecipeProcedure;

import jakarta.transaction.Transactional;

public interface RecipeProcedureRepository extends JpaRepository<RecipeProcedure, Long> {
	
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM recipe_procedure WHERE recipe_seq=?1 ORDER BY recipepro_seq ASC", nativeQuery = true)
	List<RecipeProcedure> getRecipeProcedureList(long recipe_seq);
	
	@Query(value="SELECT * FROM recipe_procedure WHERE recipe_seq=?1", nativeQuery = true)
	RecipeProcedure getRecipeProcedure(long recipe_seq);
	
	@Transactional
	@Modifying
	@Query(value="DELETE recipe_procedure WHERE recipe_seq=?1", nativeQuery = true)
	void deleteByRecipeSeq(long recipe_seq);
}
