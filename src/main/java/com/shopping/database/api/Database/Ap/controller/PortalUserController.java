package com.shopping.database.api.Database.Ap.controller;

import com.shopping.database.api.Database.Ap.model.PortalUser;
import com.shopping.database.api.Database.Ap.repository.PortalUserRepo;
import com.shopping.database.api.Database.Ap.util.ShoppingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/db/user")
public class PortalUserController {
    @Autowired
    PortalUserRepo portalUserRepo;
   @PostMapping("/add")
   public PortalUser addUser(@RequestBody PortalUser portalUser) {
        portalUserRepo.save(portalUser);
        return portalUser;
    }
    @GetMapping("/getuser")
    public PortalUser getUserById(@RequestParam UUID id){
        ShoppingLogger.logger.info("Call recived database");
        return portalUserRepo.findById(id).orElse(null);
    }
}
