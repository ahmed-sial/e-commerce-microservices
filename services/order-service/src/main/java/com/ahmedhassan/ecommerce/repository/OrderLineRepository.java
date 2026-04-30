package com.ahmedhassan.ecommerce.repository;

import com.ahmedhassan.ecommerce.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
}
