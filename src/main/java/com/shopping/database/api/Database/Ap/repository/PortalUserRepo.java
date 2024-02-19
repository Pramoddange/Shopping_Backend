package com.shopping.database.api.Database.Ap.repository;

import com.shopping.database.api.Database.Ap.model.PortalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
@Repository
public interface PortalUserRepo extends JpaRepository<PortalUser, UUID> {

}
