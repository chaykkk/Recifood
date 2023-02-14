package com.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.entity.Funding;
import com.ezen.persistence.FundingRepository;

@Service
public class FundingServiceImpl implements FundingService {

	@Autowired
	private FundingRepository fundingRepo;
	
	@Override
	public void insertFunding(Funding funding) {
		fundingRepo.save(funding);
	}

	@Override
	public void updateFunding(Funding funding) {
		Funding findFunding = fundingRepo.findById(funding.getFunding_seq()).get();
		findFunding.setKind(funding.getKind());
		findFunding.setFunding_name(funding.getFunding_name());
		findFunding.setFunding_subname(funding.getFunding_subname());
		findFunding.setContent(funding.getContent());
		findFunding.setPrice(funding.getPrice());
		findFunding.setStartdate(funding.getStartdate());
		findFunding.setEnddate(funding.getEnddate());
		findFunding.setFilename(funding.getFilename());
		findFunding.setFilepath(funding.getFilepath());
		
		fundingRepo.save(findFunding);
	}

	@Override
	public void deleteFunding(Funding funding) {
		fundingRepo.deleteById(funding.getFunding_seq());
	}

	@Override
	public List<Funding> getAllFundingList(Funding funding) {
		return fundingRepo.findAll();
	}

	@Override
	public List<Funding> getAllFundingListByKind(String kind) {
		return fundingRepo.getAllFundingListByKind(kind);
	}

	@Override
	public List<Funding> getMyFundingList(String username) {
		return fundingRepo.getMyFundingList(username);
	}

	@Override
	public List<Funding> getMyFundingListByKind(String username, String kind) {
		return fundingRepo.getMyFundingListByKind(username, kind);
	}

	@Override
	public List<Funding> getAllFundingListByPrice(Funding funding) {
		return fundingRepo.getAllFundingListByPrice(funding);
	}

	@Override
	public List<Funding> getAllFundingListByVC(Funding funding) {
		return fundingRepo.getAllFundingListByVC(funding);
	}

	@Override
	public Funding getFunding(Funding funding) {
		return fundingRepo.findById(funding.getFunding_seq()).get();
	}

	@Override
	public void updateResult(Long recipe_seq) {
		fundingRepo.updateResult(recipe_seq);
	}

	@Override
	public int updateViewCount(Long funding_seq) {
		return fundingRepo.updateViewCount(funding_seq);
	}

	@Override
	public List<Funding> getBestFundingList(Funding funding) {
		return fundingRepo.getBestFundingList(funding);
	}

}
