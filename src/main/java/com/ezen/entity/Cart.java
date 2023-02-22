package com.ezen.entity;

import java.util.Date;

import jakarta.persistence.Column;
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
@ToString(exclude= {"member", "funding"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cart_seq;
	
	private int quantity; // 카트에 담긴 상품 개수
	@Column(insertable=false, updatable=false, columnDefinition = "date default sysdate")
	private Date regdate;
	
	@ManyToOne
	@JoinColumn(name="username", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getCartList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name="funding_seq", nullable=false, updatable=false)
	private Funding funding;
	
	public void setFunding(Funding funding) {
		this.funding = funding;
		funding.getCartList().add(this);
	}
}
