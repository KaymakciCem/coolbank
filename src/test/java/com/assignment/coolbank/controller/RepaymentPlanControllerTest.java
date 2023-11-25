package com.assignment.coolbank.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.assignment.coolbank.dto.PaymentsResponse;
import com.assignment.coolbank.dto.RepaymentPlanRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class RepaymentPlanControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    static String REPAYMENT_INFO_URL = "/v1/generate-plan";

    @Test
    void generatePlan() {

        var repaymentPlan = new RepaymentPlanRequest(24, BigDecimal.valueOf(5), BigDecimal.valueOf(5000), "01-01-2024");

        webTestClient
                .post()
                .uri(REPAYMENT_INFO_URL)
                .bodyValue(repaymentPlan)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PaymentsResponse.class)
                .consumeWith(paymentResponseExchangeResult -> {
                    var paymentResult = paymentResponseExchangeResult.getResponseBody();
                    assert Objects.requireNonNull(paymentResult).getBorrowerPayments() != null;
                });
    }
}