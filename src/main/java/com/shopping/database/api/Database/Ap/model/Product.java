package com.shopping.database.api.Database.Ap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    UUID productId;
    String productName;
    int quantity;
    int price;
    PortalUser seller;
    int rating;
    String productType;

}
