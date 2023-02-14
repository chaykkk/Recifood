package com.ezen.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Heart;
import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.entity.RecipeReply;
import com.ezen.persistence.HeartRepository;
import com.ezen.service.HeartService;
import com.ezen.service.RecipeReplyService;
import com.ezen.service.RecipeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RecipeController {
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private RecipeReplyService recipeReplyService;
	@Autowired
	private HeartService heartService;
	@Autowired
	private HeartRepository heartRepository;
	
	@GetMapping("/recipeList")
	public String recipeList(Recipe recipe, Model model) {
		List<Recipe> rp = recipeService.getAllRecipeList(recipe);
		model.addAttribute("recipe", rp);
		return "recipe/recipeList";
	}
	
	@GetMapping("/recipeListKind")
	public String recipeListByKind(Recipe recipe, Model model) {
		List<Recipe> rp = recipeService.getAllRecipeListByKind(recipe.getKind());
		model.addAttribute("recipe", rp);
		return "recipe/recipeListKind";
	}
	
	@GetMapping("/recipeListDESC")
	public String recipeListDESC(Recipe recipe, Model model) {
		List<Recipe> rp = recipeService.getRecipeListDESC(recipe);
		model.addAttribute("recipe", rp);
		return "recipe/recipeListDESC";
	}
	
	@GetMapping("/recipeListGood")
	public String recipeListGood(Recipe recipe, Model model) {
		List<Recipe> rp = recipeService.getRecipeListGood(recipe);
		model.addAttribute("recipe", rp);
		return "recipe/recipeListGood";
	}
	
	
	@GetMapping("/getRecipe")
	public String getRecipe(Recipe recipe, Model model, RecipeReply recipeReply, Heart heart, HttpSession session) {
		Recipe rp = recipeService.getRecipe(recipe);
		recipeReply.setRecipe(rp);
		List<RecipeReply> rrp = recipeReplyService.getRecipeReplyList(recipeReply);
		rp.setGood(heartRepository.totalHeart(rp.getRecipe_seq())); // 레시피 추천수
		//recipeReplyService.replyCount(recipeReply.getRecipe().getRecipe_seq());
		model.addAttribute("recipe", rp);
		model.addAttribute("recipeReply", rrp);
		model.addAttribute("cnt", recipeReplyService.replyCount(recipeReply.getRecipe().getRecipe_seq())); // 댓글 수 표시
		Member loginMember = (Member) session.getAttribute("loginMember");

		if(loginMember != null) { // 로그인한 멤버가 있으면 해당 멤버의 좋아요 가져오기
			heart.setRecipe(rp);
			heart.setMember(loginMember);
			model.addAttribute("heart", heartService.getHeart(heart));
		}
		return "recipe/getRecipe";
		
	}
	
	// 나의 레시피 목록 조회
	@GetMapping("/myRecipeList")
	public String myRecipeList(HttpSession session, Model model, Recipe recipe) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			recipe.setMember(loginMember);
			List<Recipe> recipeList = recipeService.getRecipeList(recipe.getMember().getUsername());
			model.addAttribute("recipeList", recipeList);
			return "recipe/myRecipeList";
		}
	}
	
	// 카테고리별 레시피 목록 조회
	@GetMapping("/getMyRecipeListKind")
	public String myRecipeListKind(HttpSession session, Model model, Recipe recipe) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			recipe.setMember(loginMember);
			List<Recipe> recipeList = recipeService.getRecipeListByKind(recipe.getMember().getUsername(), recipe.getKind());
			model.addAttribute("recipeList", recipeList);
			return "recipe/myRecipeListKind";
		}
	}
	
	// 레시피 상세 조회
	@GetMapping("/getMyRecipe")
	public String getMyRecipe(HttpSession session, Model model, Recipe recipe, RecipeReply recipeReply) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			recipe.setMember(loginMember);
			Recipe rp = recipeService.getRecipe(recipe);
			recipeReply.setRecipe(rp);
			List<RecipeReply> rrp = recipeReplyService.getRecipeReplyList(recipeReply);
			model.addAttribute("recipe", rp);
			model.addAttribute("recipeReply", rrp);
			model.addAttribute("cnt", recipeReplyService.replyCount(recipeReply.getRecipe().getRecipe_seq())); // 댓글 수 표시
			return "recipe/getMyRecipe";
		}
	}
	
	@GetMapping("/insertRecipe")
	public String insertRecipe() {
		
		return "recipe/insertRecipe";
	}
	
	@PostMapping("/insertRecipe")
	public String insertRecipe(HttpSession session, Recipe recipe, MultipartFile file) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			// 저장 경로 지정
	    	String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
	    	
	    	// 식별자, 랜덤으로 이름 생성
	    	UUID uuid = UUID.randomUUID();
	    	
	    	// 랜덤식별자_원본파일이름 = 저장될 파일이름 지정
	    	String fileName = uuid + "_" + file.getOriginalFilename();
	    	
	    	// 이름 name, projectPath 경로에 저장
	    	File saveFile = new File(projectPath, fileName);
	    	
	    	file.transferTo(saveFile);
	    	
	    	// DB에 파일 넣기
	    	recipe.setFilename(fileName);
	    	// 저장 경로
	    	recipe.setFilepath("/files/" + fileName);
	    	
	    	// 파일 저장
	    	recipe.setMember(loginMember);
	    	recipeService.insertRecipe(recipe);
	    	return "redirect:myRecipeList";
		}
	}
	
	@GetMapping("/updateRecipe")
	public String updateRecipeView(HttpSession session, Recipe recipe, Model model) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			recipe.setMember(loginMember);
			Recipe rp = recipeService.getRecipe(recipe);
			model.addAttribute("recipe", rp);
			return "recipe/updateRecipe";
		}
	}
	
	
	@PostMapping("/updateRecipe")
	public String updateRecipe(HttpSession session, Recipe recipe, MultipartFile file) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
	    	
	    	// 식별자, 랜덤으로 이름 생성
	    	UUID uuid = UUID.randomUUID();
	    	
	    	// 랜덤식별자_원본파일이름 = 저장될 파일이름 지정
	    	String fileName = uuid + "_" + file.getOriginalFilename();
	    	
	    	// 이름 name, projectPath 경로에 저장
	    	File saveFile = new File(projectPath, fileName);
	    	
	    	file.transferTo(saveFile);
	    	
	    	// DB에 파일 넣기
	    	recipe.setFilename(fileName);
	    	// 저장 경로
	    	recipe.setFilepath("/files/" + fileName);
	    	
	    	// 파일 저장
	    	recipe.setMember(loginMember);
	    	recipeService.updateRecipe(recipe);
	    	return "redirect:myRecipeList";
		}
	}
	
	@GetMapping("/deleteRecipe")
	public String deleteRecipe(HttpSession session, Recipe recipe) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			recipe.setMember(loginMember);
			recipeService.deleteRecipe(recipe);
			return "redirect:myRecipeList";
		}
	}
}
