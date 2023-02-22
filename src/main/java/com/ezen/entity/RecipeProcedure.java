package com.ezen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude={"member", "recipe"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RecipeProcedure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipepro_seq;
	
	private String procedure;
	private String filename;
	private String filepath;
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getRecipeProcedureList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name = "recipe_seq")
	private Recipe recipe;
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		recipe.getRecipeProcedureList().add(this);
	}
}
