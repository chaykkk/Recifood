package com.ezen.controller;

import com.ezen.entity.Heart;
import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.service.HeartServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
@Log4j2
public class HeartController {

    @Autowired
    HeartServiceImpl heartService;

    @PostMapping("/heart")
    @ResponseBody
    public int insertHeart(@RequestBody Map<String, String> map) {
        System.out.println("사용자명: "+map.get("username"));
        System.out.println("레시피 Seq: " + map.get("recipe_seq"));

        Member member = new Member();
        member.setUsername(map.get("username"));
        Recipe recipe = new Recipe();
        recipe.setRecipe_seq(Long.parseLong(map.get("recipe_seq")));

        Heart heart = new Heart();
        heart.setMember(member);
        heart.setRecipe(recipe);

        log.info(heart);
        int heart_val = heartService.insertHeart(heart);

        return heart_val;
    }
}
