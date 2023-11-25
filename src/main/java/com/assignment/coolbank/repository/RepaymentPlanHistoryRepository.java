package com.assignment.coolbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.coolbank.domain.RepaymentPlanHistory;

@Repository
public interface RepaymentPlanHistoryRepository extends JpaRepository<RepaymentPlanHistory, Long> {
}
