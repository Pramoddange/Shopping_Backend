package com.shopping.database.api.Database.Ap.controller;

import com.shopping.database.api.Database.Ap.DTO.Response.ACLConfigDTO;
import com.shopping.database.api.Database.Ap.DTO.request.AddAccessDTO;
import com.shopping.database.api.Database.Ap.model.ACL;
import com.shopping.database.api.Database.Ap.repository.ACLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db/acl")
public class ACLController {
    @Autowired
    ACLRepository aclRepository;
    @PostMapping("/add")
    public void registerAccess(@RequestBody AddAccessDTO addAccessDTO){
        ACL acl=new ACL();
        acl.setRequester(addAccessDTO.getRequester());
        acl.setOperation(addAccessDTO.getOperation());
        aclRepository.save(acl);

    }
    @GetMapping("/validate")
    public ResponseEntity isAccessAvailable(@RequestParam String requester, @RequestParam String operation){
        ACL acl = aclRepository.getConfiguration(requester,operation);
        if(acl != null){
            return new ResponseEntity<>(new ACLConfigDTO(true), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ACLConfigDTO(false), HttpStatus.OK);
        }
    }
}
