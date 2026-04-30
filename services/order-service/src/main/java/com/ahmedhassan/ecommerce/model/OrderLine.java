package com.ahmedhassan.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Min(1)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @NotNull
    private UUID productId;
}
