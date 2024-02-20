package com.shopping.database.api.Database.Ap.repository;

import com.shopping.database.api.Database.Ap.model.ACL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ACLRepository extends JpaRepository<ACL, UUID> {
}
