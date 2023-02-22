package com.ezen.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ezen.entity.Funding;

import jakarta.transaction.Transactional;

public interface FundingRepository extends JpaRepository<Funding, Long>, QuerydslPredicateExecutor<Funding> {
	// 전체 펀딩 목록 조회
	@Query("SELECT fd FROM Funding fd")
	Page<Funding> getAllFundingList(Funding funding, Pageable pageable);
	
	//@Query("SELECT fd FROM Funding fd")
	//List<Funding> getFundingList(Funding funding);
	
	// 카테고리별 전체 펀딩 조회
	@Query("SELECT fd FROM Funding fd WHERE fd.kind=?1")
	Page<Funding> getAllFundingListByKind(String kind ,Pageable pageable);
	
	// 가격순 전체 펀딩 조회
	@Query("SELECT fd FROM Funding fd ORDER BY fd.price DESC")
	Page<Funding> getAllFundingListByPrice(Funding funding, Pageable pageable);
	
	// 인기순 전체 펀딩 조회
	@Query("SELECT fd FROM Funding fd ORDER BY fd.viewcount DESC")
	Page<Funding> getAllFundingListByVC(Funding funding, Pageable pageable);
	
	// 나의 펀딩 목록 조회
	@Query("SELECT fd FROM Funding fd WHERE fd.member.username=?1")
	Page<Funding> getMyFundingList(String username, Pageable pageable);

	// 카테고리별 나의 펀딩 목록 조회
	@Query("SELECT fd FROM Funding fd WHERE fd.member.username=?1 AND fd.kind=?2")
	Page<Funding> getMyFundingListByKind(String username, String kind, Pageable pageable);
	
	// 펀딩 추가시 레시피 테이블에 펀딩상태 업데이트
	@Transactional
	@Modifying
	@Query(value="UPDATE recipe SET result=1 WHERE recipe_seq=?1", nativeQuery = true)
	void updateResult(long recipe_seq);
	
	// 펀딩 삭제시 레시피 테이블에 업데이트
	@Transactional
	@Modifying
	@Query(value="UPDATE recipe SET result=2 WHERE recipe_seq=?1", nativeQuery = true)
	void updateResultTwo(long recipe_seq);
	
	// 조회수 업데이트
	@Transactional
	@Modifying
	@Query("UPDATE Funding fd SET fd.viewcount = fd.viewcount+1 WHERE fd.funding_seq=?1")
	int updateViewCount(long recipe_seq);
	
	// 조회수 높은 상품 3개
	// SELECT * FROM (SELECT * FROM funding ORDER BY viewcount DESC) WHERE ROWNUM <2;
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM (SELECT * FROM funding ORDER BY viewcount DESC) WHERE ROWNUM<4", nativeQuery = true)
	List<Funding> getBestFundingList(Funding funding);
	
	@Query("select fd from Funding fd")
	Page<Funding> getFundingList(Pageable pageable);

}
