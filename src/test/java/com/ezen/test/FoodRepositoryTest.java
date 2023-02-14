package com.ezen.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ezen.entity.Food;
import com.ezen.entity.Member;
import com.ezen.persistence.FoodRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FoodRepositoryTest {
	@Autowired
	private FoodRepository foodRepo;
	
//	@Test
//	@Disabled
//	public void insertFood() throws Exception {
//		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = date1.parse("2023-03-31");
//		FoodDTO foodDTO = FoodDTO.builder()
//				.category("야채")
//				.name("고구마")
//				.exp(date)
//				.member(new Member("user2", null, null, null, null, null, null, null, null, null, null, null))
//				.build();
//
//		Food food = foodDTO.toEntity(foodDTO);
//		foodRepo.save(food);
//	}
	
	@Test
	@Disabled
	public void deteFood() {
		foodRepo.deleteById(3L);
	}
}
