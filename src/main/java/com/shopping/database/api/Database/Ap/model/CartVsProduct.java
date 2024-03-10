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
public class CartVsProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    UUID cartId;
    UUID productId;
    int totalPrice;
    int totalQuantity;
}
