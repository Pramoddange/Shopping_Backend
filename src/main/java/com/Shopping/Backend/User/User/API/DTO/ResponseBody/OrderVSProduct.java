package com.Shopping.Backend.User.User.API.DTO.ResponseBody;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class OrderVSProduct {
    UUID id;
    UUID orderId;
    UUID productId;
    int totalPrice;
    int totalQuantity;
}

