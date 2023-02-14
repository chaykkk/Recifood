package com.ezen.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude={"member", "recipe"})
@Entity
public class RecipeReply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reply_seq;
	
	private String content;
	@Column(insertable=false, updatable=false, columnDefinition = "date default sysdate")
	private Date regdate; // 등록일
	
	@Column(insertable=false, updatable=false, columnDefinition = "date default sysdate")
	private Date modifiedate; // 수정일
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getRecipeReplyList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name="recipe_seq", nullable=false, updatable=false)
	private Recipe recipe;
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		recipe.getRecipeReplyList().add(this);
	}
}
