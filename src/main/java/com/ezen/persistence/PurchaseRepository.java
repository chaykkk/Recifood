package com.ezen.persistence;

import com.ezen.entity.Purchase;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>, QuerydslPredicateExecutor<Purchase> {

    Page<Purchase> findAllByMember_Username(String username, Pageable pageable);

    @Query("select p from Purchase p")
    Page<Purchase> getPurchaseList(Pageable pageable);
}
