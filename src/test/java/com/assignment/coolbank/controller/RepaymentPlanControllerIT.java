package com.assignment.coolbank.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.assignment.coolbank.dto.PaymentsResponse;
import com.assignment.coolbank.dto.RepaymentPlanRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureWebTestClient
class RepaymentPlanControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    static String REPAYMENT_INFO_URL = "/generate-plan";

    @Container
    static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("testcontainer")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry properties){
        properties.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        properties.add("spring.datasource.username", mySQLContainer::getUsername);
        properties.add("spring.datasource.password", mySQLContainer::getPassword);
    }

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