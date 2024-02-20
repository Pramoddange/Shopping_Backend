package com.shopping.database.api.Database.Ap.controller;

import com.shopping.database.api.Database.Ap.DTO.request.AddAccessDTO;
import com.shopping.database.api.Database.Ap.model.ACL;
import com.shopping.database.api.Database.Ap.repository.ACLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db/acl")
public class ACLController {
    @Autowired
    ACLRepository aclRepository;
    @PostMapping("/add")
    public void registerAccess(@RequestBody AddAccessDTO addAccessDTO){
        ACL acl=new ACL();
        acl.setRequester(addAccessDTO.getRequestor());
        acl.setOperation(addAccessDTO.getOperation());
        aclRepository.save(acl);

    }
}
