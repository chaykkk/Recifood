package com.ezen.entity;

import java.util.Date;

public class Purchase {

    private Long purchaseSeq;
    private String comment;
    private String agree;
    private String payer; // 입금자명
    private Date regdate; // 구매일자
    private int quantity; // 구매수량
    private int result; // 진행상태 -> 1: 배송전, 2: 배송중, 3: 배송완료
    private int price; // 결제금액
}
