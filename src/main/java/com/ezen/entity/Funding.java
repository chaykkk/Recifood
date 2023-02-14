package com.ezen.entity;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude={"member", "recipe"})
@Entity
public class Funding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long funding_seq;
	
	private String kind;  // 한식, 양식, 디저트
	private String funding_name; // 밀키트 펀딩이름
	private String funding_subname; // 밀키트 펀딩 요약설명
	private String content; // 내용
	private int price; // 가격
	@Column(insertable=false, updatable=false)
	@ColumnDefault("1")
	private int result; // 1. 펀딩시작전 2. 펀딩진행중 3. 펀딩종료
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate; // 펀딩시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate; // 펀딩종료일
	@Column(insertable=false, updatable=false, columnDefinition = "date default sysdate")
	private Date regdate;
	@Column(insertable=false, updatable=false)
	@ColumnDefault("0")
	private int viewcount; // 조회수
	private String filename; // 파일이름
	private String filepath; // 파일경로
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getFundingList().add(this);
	}
	
	@OneToOne
	@JoinColumn(name="recipe_seq")
	private Recipe recipe;
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
