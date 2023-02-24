package com.ezen.test;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ezen.entity.Board;
import com.ezen.entity.Member;
import com.ezen.persistence.BoardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	@Disabled
	public void saveBoard() {

		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member11");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member1");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member2");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member3");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member4");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member5");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member6");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member7");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<10; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("member8");

			board.setTitle("게시글 테스트😐" + i);
			board.setMember(member);
			board.setCategory("2");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<15; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("admin1");

			board.setTitle("관리자 게시글 테스트😊" + i);
			board.setMember(member);
			board.setCategory("1");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
		
		for(int i=0; i<15; i++) {

			Board board = new Board();
			Member member = new Member();
			member.setUsername("admin2");

			board.setTitle("관리자 게시글 테스트😊" + i);
			board.setMember(member);
			board.setCategory("1");
			board.setContent("게시글 내용........" + i);
			board.setRegdate(new Date());
			boardRepository.save(board);
		}
	}
}
