package com.shopping.database.api.Database.Ap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ACL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String requester;
    String operation;
}
