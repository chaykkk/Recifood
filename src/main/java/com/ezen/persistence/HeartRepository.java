package com.ezen.persistence;

import com.ezen.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query("select h from Heart h where h.recipe.recipe_seq=:recipe_seq and h.member.username=:username")
    Optional<Heart> getHeart(Long recipe_seq, String username);

    @Query("select count(*) from Heart h where h.recipe.recipe_seq=:recipe_seq")
    int totalHeart(Long recipe_seq);
}
