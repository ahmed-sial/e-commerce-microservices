package com.ahmedhassan.ecommerce.service;

import com.ahmedhassan.ecommerce.dto.PaymentRequestDto;
import com.ahmedhassan.ecommerce.dto.PaymentResponseDto;
import com.ahmedhassan.ecommerce.mapper.PaymentMapper;
import com.ahmedhassan.ecommerce.model.PaymentMethod;
import com.ahmedhassan.ecommerce.nofication.NotificationProducer;
import com.ahmedhassan.ecommerce.nofication.dto.PaymentNotificationRequestDto;
import com.ahmedhassan.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public UUID createPayment(PaymentRequestDto dto) {
        var payment = repository.save(mapper.toPayment(dto));
        var paymentNotification = PaymentNotificationRequestDto
                .builder()
                .orderReference(dto.orderReference())
                .amount(dto.amount())
                .paymentMethod(dto.paymentMethod())
                .customerFirstName(dto.customer().firstName())
                .customerLastName(dto.customer().lastName())
                .customerEmail(dto.customer().email())
                .build();
        notificationProducer.sendNotification(paymentNotification);
        return payment.getId();
    }
}
