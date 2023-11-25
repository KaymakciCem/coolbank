package com.assignment.coolbank.domain;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repayment_plan_history")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public void setBorrowerPaymentAmount(BigDecimal borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(BigDecimal initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public void setRemainingOutstandingPrincipal(BigDecimal remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepaymentPlanHistory that = (RepaymentPlanHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(borrowerPaymentAmount, that.borrowerPaymentAmount) && Objects.equals(date, that.date) && Objects.equals(initialOutstandingPrincipal, that.initialOutstandingPrincipal) && Objects.equals(interest, that.interest) && Objects.equals(principal, that.principal) && Objects.equals(remainingOutstandingPrincipal, that.remainingOutstandingPrincipal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrowerPaymentAmount, date, initialOutstandingPrincipal, interest, principal, remainingOutstandingPrincipal);
    }

    @Override
    public String toString() {
        return "RepaymentPlanHistory{" +
                "id=" + id +
                ", borrowerPaymentAmount=" + borrowerPaymentAmount +
                ", date='" + date + '\'' +
                ", initialOutstandingPrincipal=" + initialOutstandingPrincipal +
                ", interest=" + interest +
                ", principal=" + principal +
                ", remainingOutstandingPrincipal=" + remainingOutstandingPrincipal +
                '}';
    }
}
