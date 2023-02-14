package com.ezen.service;

import com.ezen.entity.Heart;

public interface HeartService {

    int insertHeart(Heart heart); // 좋아요

    int getHeart(Heart heart); // 멤버의 좋아요 가져오기

}
