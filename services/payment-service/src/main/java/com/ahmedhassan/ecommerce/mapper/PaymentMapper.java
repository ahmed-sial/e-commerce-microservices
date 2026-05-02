package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.PaymentRequestDto;
import com.ahmedhassan.ecommerce.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentRequestDto dto) {
        return Payment
                .builder()
                .amount(dto.amount())
                .paymentMethod(dto.paymentMethod())
                .orderId(dto.orderId())
                .build();
    }
}
