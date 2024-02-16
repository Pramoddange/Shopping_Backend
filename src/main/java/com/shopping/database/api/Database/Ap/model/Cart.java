package com.shopping.database.api.Database.Ap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Cart {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int quantity;
    int price;
    @OneToOne
    PortalUser user;

}
