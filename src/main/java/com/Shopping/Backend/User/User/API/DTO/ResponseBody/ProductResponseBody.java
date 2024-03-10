package com.Shopping.Backend.User.User.API.DTO.ResponseBody;

import com.Shopping.Backend.User.User.API.DTO.GeneralUseDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseBody {
    UUID productId;
    String productName;
    int price;
    int quantity;
    UserDTO seller;
    double rating;
    String productType;
}