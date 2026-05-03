package com.ahmedhassan.ecommerce.repository;

import com.ahmedhassan.ecommerce.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
