package com.ezen.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	 @Query("select b from Board b where b.member.username=:username")
	 Page<Board> getMyBoardList(String username, Pageable pageable); // 자기가 쓴 게시글

	 Page<Board> findAllByCategory(String category, Pageable pageable);
}
