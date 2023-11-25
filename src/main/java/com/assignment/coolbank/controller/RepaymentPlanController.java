package com.assignment.coolbank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.coolbank.dto.PaymentsResponse;
import com.assignment.coolbank.dto.RepaymentPlanRequest;
import com.assignment.coolbank.service.RepaymentPlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RepaymentPlanController {

    private final RepaymentPlanService repaymentPlanService;

    @PostMapping("/generate-plan")
    public ResponseEntity<PaymentsResponse> generatePlan(@RequestBody @Valid final RepaymentPlanRequest request) {
        log.info("generating repayment plan with the amount of {} and with rate of {}",
                request.loanAmount(), request.nominalRate());
        return ResponseEntity.ok(repaymentPlanService.generatePlan(request));
    }
}
