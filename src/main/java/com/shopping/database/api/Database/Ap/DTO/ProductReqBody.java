package com.shopping.database.api.Database.Ap.DTO;


import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductReqBody {
    String productName;
    int quantity;
    int price;
    String productType;
    UUID sellerId;

}
