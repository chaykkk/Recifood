package com.ezen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.entity.Member;
import com.ezen.entity.QMember;
import com.ezen.entity.Search;
import com.ezen.persistence.MemberRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void insertMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void updateMember(Member member) {
		Member findMember = memberRepository.findById(member.getUsername()).get();

		findMember.setPassword(member.getPassword());
		findMember.setPhone(member.getPhone());
		findMember.setEmail(member.getEmail());
		findMember.setZip_num(member.getZip_num());
		findMember.setAddress(member.getAddress());

		memberRepository.save(member);
	}

	@Override
	public void deleteMember(Member member) {
		memberRepository.deleteById(member.getUsername());
	}

	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepository.findById(member.getUsername());

		if(findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}

	@Override
	public Page<Member> getMemberList(int page, Search search) {
		BooleanBuilder builder = new BooleanBuilder();

		QMember qMember = QMember.member;

		if(search.getSearchCondition().equals("USERNAME")) {
			builder.and(qMember.username.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("NAME")) {
			builder.and(qMember.name.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("EMAIL")) {
			builder.and(qMember.email.like("%" + search.getSearchKeyword() + "%"));
		}
		Pageable pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "regdate");
		return memberRepository.findAll(builder, pageable);
	}
	
	@Override
	public Member findMemberId(String name, String email) {
		return memberRepository.findMemberId(name, email);
	}

	@Override
	public Member findMemberPwd(String username, String email) {
		return memberRepository.findMemberPwd(username, email);
	}


}
