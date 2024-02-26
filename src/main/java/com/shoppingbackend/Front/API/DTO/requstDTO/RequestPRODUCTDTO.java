package com.shoppingbackend.Front.API.DTO.requstDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPRODUCTDTO {
    String productName;
    int price;
    int qunatity;
    UUID sellerId;
    String productType;
}