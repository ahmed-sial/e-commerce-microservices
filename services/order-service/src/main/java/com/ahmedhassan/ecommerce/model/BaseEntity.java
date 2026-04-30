package com.ahmedhassan.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createAt;
    @LastModifiedDate
    private Instant updateAt;
}
