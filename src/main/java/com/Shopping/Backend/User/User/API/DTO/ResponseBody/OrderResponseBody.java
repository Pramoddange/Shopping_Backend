package com.Shopping.Backend.User.User.API.DTO.ResponseBody;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponseBody {
    UUID userId;
    UUID orderId;
    double totalPrice;
    int totalQuantity;
}
