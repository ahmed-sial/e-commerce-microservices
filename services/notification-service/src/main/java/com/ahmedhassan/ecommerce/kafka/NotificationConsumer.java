package com.ahmedhassan.ecommerce.kafka;

import com.ahmedhassan.ecommerce.dto.order.OrderConfirmationDto;
import com.ahmedhassan.ecommerce.dto.payment.PaymentConfirmationDto;
import com.ahmedhassan.ecommerce.email.EmailService;
import com.ahmedhassan.ecommerce.model.Notification;
import com.ahmedhassan.ecommerce.model.NotificationType;
import com.ahmedhassan.ecommerce.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService service;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmationDto dto) throws MessagingException {
        repository.save(
                Notification
                        .builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(Instant.now())
                        .paymentConfirmation(dto)
                        .build()
        );
        var customerName = dto.customerFirstname() + " " + dto.customerLastname();
        service.sendPaymentSuccessEmail(dto.customerEmail(), customerName, dto.amount(), dto.orderReference());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmationDto dto) throws MessagingException {
        repository.save(
                Notification
                        .builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(Instant.now())
                        .orderConfirmation(dto)
                        .build()
        );
        var customerName = dto.customer().firstname() + " " + dto.customer().lastname();
        service.sendOrderConfirmationEmail(
                dto.customer().email(),
                customerName,
                dto.totalAmount(),
                dto.orderReference(),
                dto.products());
    }
}
