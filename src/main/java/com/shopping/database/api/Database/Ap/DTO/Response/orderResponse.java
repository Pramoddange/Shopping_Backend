package com.shopping.database.api.Database.Ap.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class orderResponse {
    UUID id;
    int quantity;
    double totalPrice;
    boolean isDelivered;
}

