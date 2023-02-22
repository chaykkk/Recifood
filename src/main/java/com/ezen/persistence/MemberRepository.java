package com.ezen.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, String>, QuerydslPredicateExecutor<Member> {
    @Query(value = "select m from Member m where m.name=:name and m.email=:email")
    Member findMemberId(String name, String email); // 아이디 찾기

    @Query(value = "select m from Member m where m.username=:username and m.email=:email")
    Member findMemberPwd(String username, String email); // 비밀번호 찾기

    @Query("select m from Member m")
    Page<Member> getMemberList(Pageable pageable);
}
