package com.ahmedhassan.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   private String name;
   private String description;
   private BigDecimal price;
   private Integer availableQuantity;

   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;
}
