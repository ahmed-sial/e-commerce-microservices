package com.ahmedhassan.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private String description;
    @NotNull
    @Column(unique = true)
    private String slug;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
