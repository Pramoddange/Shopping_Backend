package com.shopping.database.api.Database.Ap.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class addOrder {
    UUID userId;
    int quantity;
    int totalPrice;
}
