package com.ezen.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
@ToString(exclude="member")
@Entity
public class Food {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long food_seq; // 식자재번호
	
	private String category; // 대분류
	private String name; // 소분류
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exp; // 유통기한
	
	@Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
	private Date regdate;
	
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getFoodList().add(this);
	}

}
