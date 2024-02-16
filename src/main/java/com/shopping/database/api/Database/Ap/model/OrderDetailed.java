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
public class OrderDetailed {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 UUID id;
 int quantity;
 double totalPrice;
 boolean isDelivered;
 @ManyToOne
    PortalUser user;
}
