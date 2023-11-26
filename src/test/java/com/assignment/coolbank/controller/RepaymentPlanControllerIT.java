package com.assignment.coolbank.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.assignment.coolbank.IntegrationTestBase;
import com.assignment.coolbank.dto.PaymentsResponse;
import com.assignment.coolbank.dto.RepaymentPlanRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class RepaymentPlanControllerIT extends IntegrationTestBase {

    @Autowired
    private WebTestClient webTestClient;

    static String REPAYMENT_INFO_URL = "/generate-plan";

    @Test
    void should_generatePlan() {

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
                    assert Objects.requireNonNull(paymentResult).borrowerPayments() != null;
                });
    }

    @Test
    void should_fail_when_negative_values_in_request_bad_request() {

        var repaymentPlan = new RepaymentPlanRequest(-24,
                                                     BigDecimal.valueOf(-5),
                                                     BigDecimal.valueOf(-5000),
                                                     "01-01-2024");
        webTestClient
                .post()
                .uri(REPAYMENT_INFO_URL)
                .bodyValue(repaymentPlan)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .consumeWith(entityExchangeResult -> {
                    var errorMessage = entityExchangeResult.getResponseBody();
                    System.out.println("errorMessage : " + errorMessage);
                    assert errorMessage!=null;
                });
    }

    @Test
    void should_fail_when_null_values_in_request_bad_request() {

        var repaymentPlan = new RepaymentPlanRequest(24,
                                                     null,
                                                     null,
                                                     null);
        webTestClient
                .post()
                .uri(REPAYMENT_INFO_URL)
                .bodyValue(repaymentPlan)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .consumeWith(entityExchangeResult -> {
                    var errorMessage = entityExchangeResult.getResponseBody();
                    System.out.println("errorMessage : " + errorMessage);
                    assert errorMessage!=null;
                });
    }

}