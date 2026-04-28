package com.ahmedhassan.ecommerce.repository;

import com.ahmedhassan.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String name);

    List<Product> findAllByIdInOrderById(List<UUID> ids);
}
