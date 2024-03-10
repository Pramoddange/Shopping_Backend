package com.Shopping.Backend.User.User.API.DTO.RequstBody;


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