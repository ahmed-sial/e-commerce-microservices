package com.ahmedhassan.ecommerce.repository;

import com.ahmedhassan.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
