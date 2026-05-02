package com.ahmedhassan.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {
    @Value("${spring.application.topic.payment}")
    private String paymentTopic;
    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder
                .name(paymentTopic)
                .build();
    }

}
