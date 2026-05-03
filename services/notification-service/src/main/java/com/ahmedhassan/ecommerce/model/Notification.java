package com.ahmedhassan.ecommerce.model;

import com.ahmedhassan.ecommerce.dto.order.OrderConfirmationDto;
import com.ahmedhassan.ecommerce.dto.payment.PaymentConfirmationDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private Instant notificationDate;
    private OrderConfirmationDto orderConfirmation;
    private PaymentConfirmationDto paymentConfirmation;
}
