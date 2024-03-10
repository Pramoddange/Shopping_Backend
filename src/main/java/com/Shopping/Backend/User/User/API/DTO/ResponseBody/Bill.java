package com.Shopping.Backend.User.User.API.DTO.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    UUID productId;
    List<BillProductDetailsDTO> productDetails;
    int totalQuantity;
    int totalPrice;
}
