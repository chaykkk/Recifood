package com.ezen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@ToString(exclude={"member", "funding"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseSeq;

    private String name;
    private String phone;
    private String email;
    private String zip_num;
    private String address;
    private String p_comment; // 배송요청사항

    @Column(columnDefinition = "char default 'n'")
    private String agree; // 결제동의

    private String purchase_uid; // 주문번호

    private String payer; // 입금자명
    private int quantity; // 구매수량
    private int result; // 진행상태 -> 1: 배송전, 2: 배송중, 3: 배송완료
    private int price; // 결제금액
    private int payment; // 결제 방식 -> 1: 카카오페이 2: 무통장입금

    @Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
    private Date regdate; // 구매일자

    @LastModifiedDate
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name="username", nullable=false, updatable=false)
    private Member member; // 구매자 아이디

    @ManyToOne
    @JoinColumn(name="funding_seq", nullable=false, updatable=false)
    private Funding funding; // 구매 펀딩번호

}
