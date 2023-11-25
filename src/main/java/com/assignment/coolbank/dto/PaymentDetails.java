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

    //"borrowerPaymentAmount":"219.36",
    //        "date":"01-01-2024",
    //        "initialOutstandingPrincipal":"5000.00",
    //        "interest":"20.83",
    //        "principal":"198.53",
    //        "remainingOutstandingPrincipal":"4801.47"
}
