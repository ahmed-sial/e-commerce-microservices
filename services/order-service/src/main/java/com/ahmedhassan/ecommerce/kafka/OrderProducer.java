package com.ahmedhassan.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    @Value("${spring.application.topic.order}")
    private String orderTopic;
    private final KafkaTemplate<String, OrderConfirmationDto> template;


    public void sendOrderConfirmationMessage(OrderConfirmationDto orderConfirmation) {
        Message<OrderConfirmationDto> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, orderTopic)
                .build();
        template.send(message);
    }
}
