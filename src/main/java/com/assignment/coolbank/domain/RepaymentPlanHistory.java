package com.assignment.coolbank.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repayment_plan_history")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepaymentPlanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrower_payment_amount", nullable = false)
    private BigDecimal borrowerPaymentAmount;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "initial_outstanding_principal", nullable = false)
    private BigDecimal initialOutstandingPrincipal;

    @Column(name = "interest", nullable = false)
    private BigDecimal interest;

    @Column(name = "principal", nullable = false)
    private BigDecimal principal;

    @Column(name = "remaining_outstanding_principal", nullable = false)
    private BigDecimal remainingOutstandingPrincipal;

}
