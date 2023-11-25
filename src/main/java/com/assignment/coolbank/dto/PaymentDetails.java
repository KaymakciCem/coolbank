package com.assignment.coolbank.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDetails {

    private BigDecimal borrowerPaymentAmount;
    private String date;
    private BigDecimal initialOutstandingPrincipal;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal remainingOutstandingPrincipal;
}
