package com.ezen.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Member;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query(value = "select m from Member m where m.name=:name and m.email=:email")
    Member findMemberId(String name, String email); // 아이디 찾기

    @Query(value = "select m from Member m where m.username=:username and m.email=:email")
    Member findMemberPwd(String username, String email); // 비밀번호 찾기
}
