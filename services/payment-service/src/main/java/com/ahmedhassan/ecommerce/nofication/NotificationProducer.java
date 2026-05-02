package com.ahmedhassan.ecommerce.nofication;

import com.ahmedhassan.ecommerce.nofication.dto.PaymentNotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    @Value("${spring.application.topic.payment}")
    private String paymentTopic;

    private final KafkaTemplate<String, PaymentNotificationRequestDto> template;

    public void sendNotification(PaymentNotificationRequestDto request) {
        Message<PaymentNotificationRequestDto> msg = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, paymentTopic)
                .build();
        template.send(msg);
    }
}
