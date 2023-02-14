package com.ezen.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;	

@Getter
@Setter
@ToString(exclude={"member", "recipeReplyList", "funding", "heartList"})
@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipe_seq;
	
	private String kind; // 한식, 양식, 디저트
	private String recipe_name; // 레시피명
	private String content; // 레시피 설명
	private String ingredient; // 재료
	private int cooking_time; // 요리시간
	private int amount; // 요리분량
	private String degree; // 난이도
	private String procedure; // 조리방법
	private String filename; // 파일이름
	private String filepath; // 파일경로
	@Column(insertable=false, updatable=false)
	@ColumnDefault("2")
	private String result; // 펀딩 신청 여부 1. 신청 2. 신청전
	@Column(insertable=false, updatable=false, columnDefinition = "number default 0")
	private int good; // 추천수
	@Column(insertable=false, updatable=false, columnDefinition = "date default sysdate")
	private Date regdate;
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getRecipeList().add(this);
	}
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("reply_seq asc") // 댓글 정렬
	private List<RecipeReply> recipeReplyList = new ArrayList<RecipeReply>();
	
	@OneToOne(mappedBy="recipe")
	private Funding funding;
	
	public void setFunding(Funding funding) {
		this.funding = funding;
	}

	@OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Heart> heartList = new ArrayList<Heart>();
}
