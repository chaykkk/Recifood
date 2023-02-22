package com.ezen.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
    @Query("select b from Board b")
    Page<Board> getBoardList(Pageable pageable);

    Page<Board> findAllByCategory(String category, Pageable pageable);

}
