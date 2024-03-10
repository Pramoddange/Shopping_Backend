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
public class AddOrderRequestBody {
    UUID userId;
    int quantity;
    int totalPrice;
}

