package com.Shopping.Backend.User.User.API.DTO.RequstBody;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerProductRegistrationDTO {
    UUID id;
    String productName;
    int price;
    int quantity;
    UUID sellerId;
    String productType;
}
