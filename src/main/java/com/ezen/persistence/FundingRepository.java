package com.ezen.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.Funding;

import jakarta.transaction.Transactional;

public interface FundingRepository extends JpaRepository<Funding, Long> {
	
	// 카테고리별 전체 펀딩 조회
	@Query("SELECT fd FROM Funding fd WHERE fd.kind=?1")
	List<Funding> getAllFundingListByKind(String kind);
	
	// 가격순 전체 펀딩 조회
	@Query("SELECT fd FROM Funding fd ORDER BY fd.price DESC")
	List<Funding> getAllFundingListByPrice(Funding funding);
	
	// 인기순 전체 펀딩 조회
	@Query("SELECT fd FROM Funding fd ORDER BY fd.viewcount DESC")
	List<Funding> getAllFundingListByVC(Funding funding);
	
	// 나의 펀딩 목록 조회
	@Query("SELECT fd FROM Funding fd WHERE fd.member.username=?1")
	List<Funding> getMyFundingList(String username);

	// 카테고리별 나의 펀딩 목록 조회
	@Query("SELECT fd FROM Funding fd WHERE fd.member.username=?1 AND fd.kind=?2")
	List<Funding> getMyFundingListByKind(String username, String kind);
	
	// 레시피 테이블에 펀딩상태 업데이트
	@Transactional
	@Modifying
	@Query(value="UPDATE recipe SET result=1 WHERE recipe_seq=?1", nativeQuery = true)
	void updateResult(long recipe_seq);
	
	// 조회수 업데이트
	@Transactional
	@Modifying
	@Query("UPDATE Funding fd SET fd.viewcount = fd.viewcount+1 WHERE fd.funding_seq=?1")
	int updateViewCount(long funding_seq);
	
	// 조회수 높은 상품 3개
	// SELECT * FROM (SELECT * FROM funding ORDER BY viewcount DESC) WHERE ROWNUM <2;
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM (SELECT * FROM funding ORDER BY viewcount DESC) WHERE ROWNUM<4", nativeQuery = true)
	List<Funding> getBestFundingList(Funding funding);
}
