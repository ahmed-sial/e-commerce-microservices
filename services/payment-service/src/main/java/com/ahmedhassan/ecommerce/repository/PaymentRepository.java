package com.ahmedhassan.ecommerce.repository;

import com.ahmedhassan.ecommerce.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
