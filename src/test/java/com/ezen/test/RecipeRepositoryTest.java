package com.ezen.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.persistence.RecipeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeRepositoryTest {
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Test
	@Disabled
	public void saveRecipe() {

		for(int i=0; i<10; i++) {

			Recipe recipe = new Recipe();
			Member member = new Member();
			member.setUsername("member2");

			recipe.setRecipe_name("레시피"+i);
			recipe.setContent("설명"+i);
			recipe.setKind("한식");
			recipe.setIngredient("재료");
			recipe.setCooking_time(20);
			recipe.setAmount(2);
			recipe.setDegree("중");
			recipe.setFilename("29dee35f-bf31-4722-887d-55543499aae8_크로플.jpg");
			recipe.setFilepath("/files/29dee35f-bf31-4722-887d-55543499aae8_크로플.jpg");
			recipe.setMember(member);
			recipeRepo.save(recipe);
		}
		
		for(int i=0; i<10; i++) {

			Recipe recipe = new Recipe();
			Member member = new Member();
			member.setUsername("member2");

			recipe.setRecipe_name("레시피"+i);
			recipe.setContent("설명"+i);
			recipe.setKind("양식");
			recipe.setIngredient("재료");
			recipe.setCooking_time(20);
			recipe.setAmount(2);
			recipe.setDegree("중");
			recipe.setFilename("29dee35f-bf31-4722-887d-55543499aae8_크로플.jpg");
			recipe.setFilepath("/files/29dee35f-bf31-4722-887d-55543499aae8_크로플.jpg");
			recipe.setMember(member);
			recipeRepo.save(recipe);
		}
		
		for(int i=0; i<10; i++) {

			Recipe recipe = new Recipe();
			Member member = new Member();
			member.setUsername("member2");

			recipe.setRecipe_name("레시피"+i);
			recipe.setContent("설명"+i);
			recipe.setKind("디저트");
			recipe.setIngredient("재료");
			recipe.setCooking_time(20);
			recipe.setAmount(2);
			recipe.setDegree("중");
			recipe.setFilename("29dee35f-bf31-4722-887d-55543499aae8_크로플.jpg");
			recipe.setFilepath("/files/29dee35f-bf31-4722-887d-55543499aae8_크로플.jpg");
			recipe.setMember(member);
			recipeRepo.save(recipe);
		}
	}
}
