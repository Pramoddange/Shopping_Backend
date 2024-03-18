package com.Shopping.Backend.User.User.API.controller;

import com.Shopping.Backend.User.User.API.DTO.RequstBody.OrderDto;
import com.Shopping.Backend.User.User.API.DTO.ResponseBody.Bill;
import com.Shopping.Backend.User.User.API.service.BuyerService;
import com.Shopping.Backend.User.User.API.utils.ShoppingLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "BuyerController",description = "This controller all end point of buyer controller ")
@RequestMapping("/user/buyer")
public class BuyerController {
  @Autowired
    BuyerService buyerService;
  @Operation(
          summary = "this is the end pt of buyer buy the order"
  )


    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestBody OrderDto orderDto){
        ShoppingLogger.logger.info("Request Recieved : Buyer Controller : " + orderDto.toString());
        // call service
       // return buyerService.orderProduct(orderDto);
       Bill bill= buyerService.orderProduct(orderDto);

        return new ResponseEntity(bill, HttpStatus.CREATED);

    }
    @GetMapping("/order_details")
    public ResponseEntity getOrderBYId(@RequestParam UUID userId,@RequestParam UUID orderId){
        Object obj=buyerService.getOrderById(userId,orderId);
        return new ResponseEntity(obj,HttpStatus.CREATED);
    }
}
