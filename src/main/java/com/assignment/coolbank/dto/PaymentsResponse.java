package com.assignment.coolbank.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaymentsResponse {
    private List<PaymentDetails> borrowerPayments;

    public PaymentsResponse() {
        borrowerPayments = new ArrayList<>();
    }
}
