package com.ezen.service;

import java.util.List;

import com.ezen.entity.Funding;

public interface FundingService {
	void insertFunding(Funding funding);
	
	void updateFunding(Funding funding);
	
	void deleteFunding(Funding funding);
	
	List<Funding> getAllFundingList(Funding funding);
	
	List<Funding> getAllFundingListByKind(String kind);
	
	List<Funding> getAllFundingListByPrice(Funding funding);
	
	List<Funding> getAllFundingListByVC(Funding funding);
	
	List<Funding> getMyFundingList(String username);
	
	List<Funding> getMyFundingListByKind(String username, String kind);
	
	Funding getFunding(Funding funding);
	
	void updateResult(Long recipe_seq);
	
	int updateViewCount(Long funding_seq);
	
	List<Funding> getBestFundingList(Funding funding);
}
