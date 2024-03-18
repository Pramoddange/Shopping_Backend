package com.shoppingwebsite_mail.Mail.Service.controller;

import com.shoppingwebsite_mail.Mail.Service.DTO.request.AddProductDto;
import com.shoppingwebsite_mail.Mail.Service.DTO.response.GeneralMessage;
import com.shoppingwebsite_mail.Mail.Service.service.SellerMailService;
import com.shoppingwebsite_mail.Mail.Service.utils.ShoppingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/seller")
public class SellerController {

    @Autowired
    SellerMailService sellerMailService;

    @PostMapping("/addproduct")
    public ResponseEntity addProduct(@RequestBody AddProductDto addProductDto){
        ShoppingLogger.logger.info("Request recieved in addProduct Method");
        try{
            sellerMailService.sendAddProductMail(addProductDto);
            return new ResponseEntity(new GeneralMessage(true), HttpStatus.CREATED);
        }catch(Exception e){
            ShoppingLogger.logger.info("Request recieved in exception");
            return new ResponseEntity(new GeneralMessage(false), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
