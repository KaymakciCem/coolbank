package com.assignment.coolbank.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanCalculationUtil {

    private static final int DAYS_IN_MONTH = 30;
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DAYS_IN_YEAR = 360;
    public static final int SCALE = 2;

    public static BigDecimal calculateAnnuity(final BigDecimal loanAmount,
                                              final int durationInMonths,
                                              final BigDecimal nominalRate) {
        final BigDecimal monthlyNominalInterestRate = nominalRate.divide(BigDecimal.valueOf(100), SCALE, RoundingMode.HALF_EVEN)
                                                                 .divide(BigDecimal.valueOf(MONTHS_IN_YEAR), 8,
                                                                         RoundingMode.HALF_EVEN);

        final BigDecimal presentValueTimesPeriod = loanAmount.multiply(monthlyNominalInterestRate);
        double dividend = 1 - (Math.pow(monthlyNominalInterestRate.add(BigDecimal.ONE).doubleValue(), -durationInMonths));

        return presentValueTimesPeriod.divide(BigDecimal.valueOf(dividend), SCALE, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calculateInterest(final BigDecimal nominalRate,
                                               final BigDecimal initialOutstandingPrincipal) {
        return nominalRate.divide(BigDecimal.valueOf(100), SCALE, RoundingMode.HALF_EVEN)
                          .multiply(BigDecimal.valueOf(DAYS_IN_MONTH))
                          .multiply(initialOutstandingPrincipal)
                          .divide(BigDecimal.valueOf(DAYS_IN_YEAR), SCALE, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calculatePrincipal(final BigDecimal annuity,
                                                final BigDecimal interest) {
        return annuity.subtract(interest);
    }

    public static BigDecimal calculateBorrowerPaymentAmount(final BigDecimal principal,
                                                            final BigDecimal interest) {
        return principal.add(interest);
    }
}
