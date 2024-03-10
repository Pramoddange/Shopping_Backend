package com.Shopping.Backend.User.User.API.controller;

import com.Shopping.Backend.User.User.API.DTO.RequstBody.OrderDto;
import com.Shopping.Backend.User.User.API.DTO.ResponseBody.Bill;
import com.Shopping.Backend.User.User.API.service.BuyerService;
import com.Shopping.Backend.User.User.API.utils.ShoppingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/buyer")
public class BuyerController {
  @Autowired
    BuyerService buyerService;

    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto){
        ShoppingLogger.logger.info("Request Recieved : Buyer Controller : " + orderDto.toString());
        // call service
       // return buyerService.orderProduct(orderDto);
       Bill bill= buyerService.orderProduct(orderDto);

        return new ResponseEntity(bill, HttpStatus.CREATED);

    }
    @GetMapping("/order")
    public ResponseEntity getOrderBYId(@RequestParam UUID userId,@RequestParam UUID orderId){
        Object obj=buyerService.getOrderById(userId,orderId);
        return new ResponseEntity(obj,HttpStatus.CREATED);
    }
}
