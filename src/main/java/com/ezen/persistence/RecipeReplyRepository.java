package com.ezen.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.RecipeReply;

public interface RecipeReplyRepository extends JpaRepository<RecipeReply, Long> {

	@Query(value="SELECT * FROM Recipe_Reply WHERE recipe_seq=?1", nativeQuery = true)
	public List<RecipeReply> findRecipeReplyByRecipeSeq(long recipe_seq);
	
	// 레시피 댓글 수 표시
	// select count(reply_seq) from recipe_reply where recipe_seq=2;
	@Query(value="SELECT count(reply_seq) FROM Recipe_Reply WHERE recipe_seq=?1", nativeQuery = true)
	int replyCount(long recipe_seq); 
}
