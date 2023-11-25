package com.assignment.coolbank.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class LoanCalculationUtilTest {

    @Test
    void calculateAnnuity() {
        var result = LoanCalculationUtil.calculateAnnuity(BigDecimal.valueOf(5000), 24, BigDecimal.valueOf(5));
        assertThat(result).isEqualTo(BigDecimal.valueOf(219.36));
    }

    @Test
    void calculateInterest() {
        var result = LoanCalculationUtil.calculateInterest(BigDecimal.valueOf(5000), BigDecimal.valueOf(5));
        assertThat(result).isEqualTo(BigDecimal.valueOf(20.83));
    }

    @Test
    void calculatePrincipal() {
        var result = LoanCalculationUtil.calculatePrincipal(BigDecimal.valueOf(219.36), BigDecimal.valueOf(20.83));
        assertThat(result).isEqualTo(BigDecimal.valueOf(198.53));
    }

    @Test
    void calculateBorrowerPaymentAmount() {
        var result = LoanCalculationUtil.calculateBorrowerPaymentAmount(BigDecimal.valueOf(198.53), BigDecimal.valueOf(20.83));
        assertThat(result).isEqualTo(BigDecimal.valueOf(219.36));
    }
}