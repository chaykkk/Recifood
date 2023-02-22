package com.ezen.entity;

import java.util.Date;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
		name = "CartListResults",
		classes = @ConstructorResult(
			targetClass = com.ezen.entity.CartDetail.class,
			columns = {  
					@ColumnResult(name="cd_seq", type = Long.class),
	                @ColumnResult(name="funding_seq", type = Long.class),
	                @ColumnResult(name="funding_name", type = String.class),
	                @ColumnResult(name="price", type = Integer.class),
	                @ColumnResult(name="quantity", type = Integer.class),
	                @ColumnResult(name="filename", type = String.class),
	                @ColumnResult(name="cart_seq", type = Long.class),
	                @ColumnResult(name="regdate", type = Date.class),
	                @ColumnResult(name="username", type = String.class)
		})
)
@Entity // 장바구니 목록 조회용 테이블, 데이터 저장 x
public class CartDetail {
	@Id
	private Long cd_seq; 
	private Long funding_seq;
	private String funding_name;
	private int price;
	private int quantity;
	private String filename;
	private Long cart_seq;
	private Date regdate;
	private String username;

}
