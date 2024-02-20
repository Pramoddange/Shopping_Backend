package com.shopping.database.api.Database.Ap.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class OrderVSProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    UUID orderId;
    UUID productId;
}
