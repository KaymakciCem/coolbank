package com.assignment.coolbank.dto;

import java.util.List;

public record PaymentsResponse(List<PaymentDetails> borrowerPayments) {}