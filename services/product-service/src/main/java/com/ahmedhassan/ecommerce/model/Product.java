package com.ahmedhassan.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
   @NotNull
   @Column(unique = true)
   private String name;
   @NotNull
   private String description;
   @NotNull
   @Min(0)
   private BigDecimal price;
   @NotNull
   @Min(0)
   private Integer availableQuantity;

   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;
}
