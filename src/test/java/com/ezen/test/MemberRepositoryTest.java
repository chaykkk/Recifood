package com.ezen.test;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ezen.entity.Degree;
import com.ezen.entity.Member;
import com.ezen.entity.Role;
import com.ezen.persistence.MemberRepository;
import com.ezen.service.MemberService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    @Disabled
    public void saveMember() {

        Member member = new Member();
            member.setUsername("admin123");
            member.setPassword("admin123123");
            member.setName("관리자");
            member.setAddress("서울시 관악구 신림동, 201호, (신림동)");
            member.setEmail("admin123@email.com");
            member.setPhone("010-0000-0000");
            member.setZip_num("00000");
            member.setAgree("y");
            member.setDegree(Degree.valueOf("GOLD"));
            member.setRole(Role.valueOf("ADMIN"));
            member.setRegdate(new Date());

        memberRepository.save(member);

            Member member1 = new Member();
            member1.setUsername("member11");
            member1.setPassword("member1111");
            member1.setName("테스트멤버");
            member1.setAddress("서울시 관악구 신림동, 201호, (신림동)");
            member1.setEmail("test@email.com");
            member1.setPhone("010-1234-5678");
            member1.setZip_num("00123");
            member1.setAgree("y");
            member1.setDegree(Degree.valueOf("SILVER"));
            member1.setRole(Role.valueOf("MEMBER"));
            member1.setRegdate(new Date());

        memberRepository.save(member1);
    }

//    @Test
//    public void joinMember() {
//
//        Member member = Member.builder()
//                .username("user2")
//                .name("김유저")
//                .password("")
//                .phone("010-1111-2222")
//                .email("user1@email.com")
//                .zip_num("user2")
//                .address("서울시 관악구 신림동")
//                .agree("n")
//                .degree(Degree.valueOf("SILVER"))
//                .role(Role.valueOf("MEMBER"))
//                .regdate(new Date())
//                .build();
//
//        memberService.insertMember(member);
//    }
}
