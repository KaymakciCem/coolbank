package com.assignment.coolbank.dto;

import java.math.BigDecimal;

public record PaymentDetails(BigDecimal borrowerPaymentAmount,
        String date,
        BigDecimal initialOutstandingPrincipal,
        BigDecimal interest,
        BigDecimal principal,
        BigDecimal remainingOutstandingPrincipal) {}