package com.shopping.database.api.Database.Ap.model;

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
    UUID orderId;
    UUID productId;
}
