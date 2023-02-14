package com.ezen.persistence;

import com.ezen.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DynamicBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
}
