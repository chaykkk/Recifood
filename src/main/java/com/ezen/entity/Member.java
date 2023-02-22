package com.ezen.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString(exclude= {"foodList", "recipeList", "recipeReplyList", "boardList", "fundingList", "boardReplyList", "heartList", "recipeProcedureList", "cartList", "purchaseList"})
@Getter
@Setter
@Valid	// 유효성 검사
@NoArgsConstructor
@AllArgsConstructor // 생성자 자동 생성
@Entity
public class Member {
	@Id
	@Pattern(regexp = "^[a-zA-z0-9].{5,20}$",
			message = "영문 대소문자, 숫자포함하여 5자리 이상 입력해 주세요.")
	private String username;

	@Pattern(regexp = "^[a-zA-z0-9].{8,20}$",
			message = "영문 대소문자, 숫자포함하여 8자리 이상 입력해 주세요.")
	private String password;
	private String name;
	private String phone;

	@Email(message = "이메일 형식에 맞게 입력해 주세요.") // 이메일 형식의 값이 들어와야 한다.
	private String email;

	private String zip_num;
	private String address;

	@Column(columnDefinition = "char default 'n'")
	private String agree; // 동의 여부

	@Enumerated(EnumType.STRING)
	private Degree degree; // 회원 등급

	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
	private Date regdate;


	@Transient // 특정 필드를 컬럼에 매핑하지 않음 DB에 저장, 조회가 되지 않는다.
	private String passwordCheck;

	@OneToMany(mappedBy="member", fetch=FetchType.EAGER) // 식자재
	private List<Food> foodList = new ArrayList<Food>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // 게시판
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER) // 레시피
	private List<Recipe> recipeList = new ArrayList<Recipe>();
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER) // 레시피 댓글
	private List<RecipeReply> recipeReplyList = new ArrayList<RecipeReply>();
	
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER) // 펀딩
	private List<Funding> fundingList = new ArrayList<Funding>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY) // 게시판 리플
	private List<BoardReply> boardReplyList = new ArrayList<BoardReply>();

	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // 좋아요
	private List<Heart> heartList = new ArrayList<Heart>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // 레시피 조리방법
	private List<RecipeProcedure> recipeProcedureList = new ArrayList<RecipeProcedure>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // 장바구니
	private List<Cart> cartList = new ArrayList<Cart>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // 결제
	private List<Purchase> purchaseList = new ArrayList<Purchase>();
}
