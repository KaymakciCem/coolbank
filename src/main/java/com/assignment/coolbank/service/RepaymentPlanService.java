package com.assignment.coolbank.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.coolbank.domain.RepaymentPlanHistory;
import com.assignment.coolbank.dto.PaymentDetails;
import com.assignment.coolbank.dto.PaymentsResponse;
import com.assignment.coolbank.dto.RepaymentPlanRequest;
import com.assignment.coolbank.exception.RepaymentInvalidDateException;
import com.assignment.coolbank.repository.RepaymentPlanHistoryRepository;
import com.assignment.coolbank.util.LoanCalculationUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepaymentPlanService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final RepaymentPlanHistoryRepository repository;

    @Transactional
    public PaymentsResponse generatePlan(final RepaymentPlanRequest request) {
        final LocalDate startDate = LocalDate.parse(request.startDate(), FORMATTER);

        if (startDate.isBefore(LocalDate.now())) {
            log.error("Start is in the past");
            throw new RepaymentInvalidDateException("Start date must be in the future");
        }

        final BigDecimal annuity = LoanCalculationUtil.calculateAnnuity(request.loanAmount(),
                                                                        request.duration(),
                                                                        request.nominalRate());

        BigDecimal principal = BigDecimal.ZERO;
        BigDecimal initialOutstandingPrincipal = request.loanAmount().subtract(principal);
        BigDecimal remainingOutstandingPrincipal;
        final PaymentsResponse paymentsResponse = new PaymentsResponse();

        for (int i = 0; i < request.duration(); i++) {
            final BigDecimal interest = calculateInterest(request.nominalRate(),
                                                    initialOutstandingPrincipal);

            principal = calculatePrincipal(annuity, interest, initialOutstandingPrincipal);

            final BigDecimal borrowerPaymentAmount = calculateBorrowerPaymentAmount(principal, interest);

            remainingOutstandingPrincipal = initialOutstandingPrincipal.subtract(principal);
            initialOutstandingPrincipal = initialOutstandingPrincipal.subtract(principal);

            final PaymentDetails paymentDetails = PaymentDetails.builder()
                                                                .borrowerPaymentAmount(borrowerPaymentAmount)
                                                                .date(LocalDate.parse(request.startDate(), FORMATTER).plusMonths(i).toString())
                                                                .initialOutstandingPrincipal(initialOutstandingPrincipal.add(principal))
                                                                .interest(interest)
                                                                .principal(principal)
                                                                .remainingOutstandingPrincipal(remainingOutstandingPrincipal)
                                                                .build();

            paymentsResponse.getBorrowerPayments().add(paymentDetails);
        }

        repository.saveAll(toPaymentPlanHistory(paymentsResponse));

        return paymentsResponse;
    }

    private List<RepaymentPlanHistory> toPaymentPlanHistory(final PaymentsResponse paymentsResponse) {
        final List<RepaymentPlanHistory> dataList = new ArrayList<>();

        paymentsResponse.getBorrowerPayments()
                        .forEach(c-> {
                            final RepaymentPlanHistory repaymentPlanHistory = new RepaymentPlanHistory();
                            repaymentPlanHistory.setDate(c.getDate());
                            repaymentPlanHistory.setPrincipal(c.getPrincipal());
                            repaymentPlanHistory.setInterest(c.getInterest());
                            repaymentPlanHistory.setBorrowerPaymentAmount(c.getBorrowerPaymentAmount());
                            repaymentPlanHistory.setInitialOutstandingPrincipal(c.getInitialOutstandingPrincipal());
                            repaymentPlanHistory.setRemainingOutstandingPrincipal(c.getRemainingOutstandingPrincipal());
                            dataList.add(repaymentPlanHistory);
                        });

        return dataList;
    }

    private BigDecimal calculateInterest(final BigDecimal nominalRate, final BigDecimal initialOutstandingPrincipal) {
        return LoanCalculationUtil.calculateInterest(nominalRate, initialOutstandingPrincipal);
    }

    private BigDecimal calculatePrincipal(final BigDecimal annuity,
                                          final BigDecimal interest,
                                          final BigDecimal initialOutstandingPrincipal) {
        return LoanCalculationUtil.calculatePrincipal(annuity, interest).min(initialOutstandingPrincipal);
    }

    private BigDecimal calculateBorrowerPaymentAmount(final BigDecimal principal, final BigDecimal interest) {
        return LoanCalculationUtil.calculateBorrowerPaymentAmount(principal, interest);
    }
}
