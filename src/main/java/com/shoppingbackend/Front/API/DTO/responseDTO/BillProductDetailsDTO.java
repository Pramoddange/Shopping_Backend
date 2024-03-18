package com.shoppingbackend.Front.API.DTO.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillProductDetailsDTO {
    UUID productId;
    String productName;
    int quantity;
    int price;
}