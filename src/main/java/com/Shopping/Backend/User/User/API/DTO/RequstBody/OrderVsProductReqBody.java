package com.Shopping.Backend.User.User.API.DTO.RequstBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderVsProductReqBody {
    UUID orderId;
    UUID productId;
    int Price;
    int Quantity;

}
