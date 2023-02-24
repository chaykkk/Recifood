package com.ezen.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ezen.dto.Email;
import com.ezen.entity.Member;
import com.ezen.entity.QMember;
import com.ezen.entity.Search;
import com.ezen.persistence.MemberRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private JavaMailSender javaMailSender;

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

	// 메일 내용 생성 및 임시비밀번호로 회원비밀번호 변경
	@Override
	public Email sendEmailAndChangePassword(String memberEmail) {
		String tempPwd = UUID.randomUUID().toString().replace("-", ""); // 랜덤으로 임시비밀번호 생성
		tempPwd = tempPwd.substring(0, 10);

		Email email = new Email();
		email.setAddress(memberEmail);
		email.setTitle("[Recifood] 임시비밀번호가 발급되었습니다.");
		email.setMessage("Recifood에서 요청하신 임시 비밀번호는" + tempPwd + "입니다." +
				"임시비밀번호로 로그인 후에는 반드시 새로운 비밀번호로  변경해 주시기 바랍니다.");

		updatePassword(tempPwd, memberEmail); // 임시 비밀번호로 회원정보 변경
		return email;
	}
	
	// 임시비밀번호로 비밀번호 변경
	@Override
	public void updatePassword(String tempPwd, String memberEmail) {

		String newPassword = tempPwd;
		Member findMember = memberRepository.findEmailMember(memberEmail); // 멤버 이메일을 찾고

		findMember.setPassword(newPassword); // 해당 이메일을 가진 멤버 비밀번호를 임시비밀번호로 변경

		memberRepository.save(findMember);
	}
	
	// 이메일 보내기
	@Override
	public void sendEmail(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(email.getAddress());
		mailMessage.setSubject(email.getTitle());
		mailMessage.setText(email.getMessage());
		mailMessage.setFrom("recifood@naver.com");
		mailMessage.setReplyTo("recifood@naver.com");
		javaMailSender.send(mailMessage);
	}
	
}
