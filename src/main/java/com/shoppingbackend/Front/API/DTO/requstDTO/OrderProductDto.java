package com.shoppingbackend.Front.API.DTO.requstDTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductDto {

    UUID productId;
    int quantity;
}