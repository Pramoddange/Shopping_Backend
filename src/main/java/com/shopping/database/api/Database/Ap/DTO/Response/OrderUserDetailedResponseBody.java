package com.shopping.database.api.Database.Ap.DTO.Response;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderUserDetailedResponseBody {
    UUID userId;
    UUID orderId;
    double totalPrice;
    int totalQuantity;
}
