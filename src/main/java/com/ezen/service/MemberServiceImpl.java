package com.ezen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ezen.entity.Member;
import com.ezen.persistence.MemberRepository;
import org.springframework.stereotype.Service;

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
	public List<Member> getMemberList(Member Member) {
		// TODO Auto-generated method stub
		return null;
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
