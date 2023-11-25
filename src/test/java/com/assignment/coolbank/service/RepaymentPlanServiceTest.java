package com.assignment.coolbank.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.coolbank.dto.RepaymentPlanRequest;
import com.assignment.coolbank.repository.RepaymentPlanHistoryRepository;

@ExtendWith(MockitoExtension.class)
class RepaymentPlanServiceTest {
    @Mock
    private RepaymentPlanHistoryRepository repository;

    @InjectMocks
    private RepaymentPlanService repaymentPlanService;

    @Test
    void generatePlan_principal_amount_does_not_exceed_initialOutstandingPrincipal() {
        var result = new RepaymentPlanRequest(24,
                                              BigDecimal.valueOf(5),
                                              BigDecimal.valueOf(7000),
                                              "01-01-2024");
        repaymentPlanService.generatePlan(result);
    }

    @Test
    void generatePlan_principal_amount_exceeds_initialOutstandingPrincipal() {
        var result = new RepaymentPlanRequest(24,
                                              BigDecimal.valueOf(5),
                                              BigDecimal.valueOf(5000),
                                              "01-01-2024");
        repaymentPlanService.generatePlan(result);
    }

}