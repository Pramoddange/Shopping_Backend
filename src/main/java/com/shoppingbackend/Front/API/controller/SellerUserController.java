package com.shoppingbackend.Front.API.controller;

import com.shoppingbackend.Front.API.DTO.GeneralMessageDTO;
import com.shoppingbackend.Front.API.DTO.UserSingUPDTO;
import com.shoppingbackend.Front.API.DTO.requstDTO.ACLConfigDTO;
import com.shoppingbackend.Front.API.DTO.requstDTO.RequestPRODUCTDTO;
import com.shoppingbackend.Front.API.DTO.requstDTO.SellerProductRegistrationDTO;
import com.shoppingbackend.Front.API.ShoppingLoggers;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/user/seller")
public class SellerUserController {

    @PostMapping("/register")
    public ResponseEntity registerProduct(@RequestBody RequestPRODUCTDTO requestPRODUCTDTO){
        // SellerID -> With the help of sellerId i can get the user object
        // call DB API to get user object
        ShoppingLoggers.logger.info("start"+requestPRODUCTDTO.getQuantity());
        ShoppingLoggers.logger.info("start"+requestPRODUCTDTO.getPrice());
        ShoppingLoggers.logger.info("start"+requestPRODUCTDTO.getProductName());
        UUID sellerId = requestPRODUCTDTO.getSellerId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(headers);
        //URL url = new URL("http://localhost:8083/db/user/getuser");

        String urlDB = "http://localhost:8080/db/user/getuser?id=" + sellerId;
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<UserSingUPDTO> respUser = restTemplate.exchange(urlDB, HttpMethod.GET, httpEntity, UserSingUPDTO.class);

        // database api for acl validation check
        ShoppingLoggers.logger.info("start"+requestPRODUCTDTO.getQuantity());
        String urlAcl = "http://localhost:8080/db/acl/validate?"+"requester="+respUser.getBody().getType()+"&"+"operation="+"ADDPRODUCT";
        ResponseEntity<ACLConfigDTO> aclResp = restTemplate.exchange(urlAcl, HttpMethod.GET, httpEntity, ACLConfigDTO.class);

        if(aclResp.getBody().isValidConfig() == false){
            return new ResponseEntity(new GeneralMessageDTO(String.format("User with id %id does not have access to perform this operation", sellerId.toString())), HttpStatus.UNAUTHORIZED);
        }

        String sellerUserURL = "http://localhost:8086/user/seller/product/register";

        HttpEntity httpEntity1 = new HttpEntity(requestPRODUCTDTO, headers);

        ShoppingLoggers.logger.info("start"+requestPRODUCTDTO.getQuantity());
        ResponseEntity<SellerProductRegistrationDTO> sellerResp = restTemplate.exchange(sellerUserURL, HttpMethod.POST, httpEntity1, SellerProductRegistrationDTO.class);
        ShoppingLoggers.logger.info(""+sellerResp .getBody().getQuantity());
        return sellerResp;

    }
}