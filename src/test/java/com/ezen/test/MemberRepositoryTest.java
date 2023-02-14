package com.ezen.test;

import com.ezen.entity.Degree;
import com.ezen.entity.Member;
import com.ezen.entity.Role;
import com.ezen.persistence.MemberRepository;
import com.ezen.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    public void saveMember() {

        Member member = new Member();
            member.setUsername("test");
            member.setPassword("test");
            member.setName("테스트멤버");
            member.setAddress("서울시 관악구 신림동");
            member.setEmail("test@email.com");
            member.setPhone("010-1234-5678");
            member.setZip_num("00123");
            member.setAgree("y");
            member.setDegree(Degree.valueOf("SILVER"));
            member.setRole(Role.valueOf("MEMBER"));
            member.setRegdate(new Date());

        memberRepository.save(member);
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
