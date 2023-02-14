package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.entity.RecipeReply;
import com.ezen.service.RecipeReplyService;
import com.ezen.service.RecipeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RecipeReplyController {

	@Autowired
	private RecipeReplyService recipeReplyService;
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/insertRecipeReply")
	public String insertRecipeReply(@RequestParam(value="recipe_seq") long recipe_seq, 
									HttpSession session, RecipeReply recipeReply, RedirectAttributes model) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		if (loginMember == null) {
			return "sign/login";
		} else {
			recipeReply.setMember(loginMember);
			Recipe recipe = new Recipe();
			recipe.setRecipe_seq(recipe_seq);
			
			recipe = recipeService.getRecipe(recipe);
			recipeReply.setRecipe(recipe);
			recipeReplyService.insertRecipeReply(recipeReply);
			
			model.addAttribute("recipe_seq", recipe_seq);
			return "redirect:getRecipe";
		}
	}
}
