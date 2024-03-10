package com.shopping.database.api.Database.Ap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
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
    int totalPrice;
    int totalQuantity;
}
