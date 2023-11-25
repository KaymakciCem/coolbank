package com.assignment.coolbank.dto;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;

@Validated
public record RepaymentPlanRequest(
        @Positive(message = "Duration must be positive")
        int duration, // (number of instalments in months)
        @NotNull(message = "Nominal must not be empty")
        @Positive(message = "Nominal interest must be positive")
        BigDecimal nominalRate, // (annual interest rate)
        @NotNull(message = "Loan amount must not be empty")
        @Positive(message = "Loan amount must be positive")
        BigDecimal loanAmount, // (principal amount)
        @NotEmpty(message = "Payout date should be in a format of dd-MM-yyyy")
        String startDate // localdate - date of disbursement/payout ("startDate")
        ) {}
