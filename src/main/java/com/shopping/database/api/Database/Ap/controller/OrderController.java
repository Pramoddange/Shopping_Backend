package com.shopping.database.api.Database.Ap.controller;

import com.shopping.database.api.Database.Ap.DTO.Response.OrderUserDetailedResponseBody;
import com.shopping.database.api.Database.Ap.DTO.Response.orderResponse;
import com.shopping.database.api.Database.Ap.DTO.request.OrderVsProductReqBody;
import com.shopping.database.api.Database.Ap.DTO.request.addOrder;
import com.shopping.database.api.Database.Ap.model.OrderDetailed;
import com.shopping.database.api.Database.Ap.model.OrderVSProduct;
import com.shopping.database.api.Database.Ap.model.PortalUser;
import com.shopping.database.api.Database.Ap.repository.OrderDetailedRepository;
import com.shopping.database.api.Database.Ap.repository.OrderVsProductRepository;
import com.shopping.database.api.Database.Ap.repository.PortalUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/db/order")
public class OrderController {
    @Autowired
    OrderDetailedRepository orderDetailedRepository;
    @Autowired
    PortalUserRepo portalUserRepo;
    @Autowired
    OrderVsProductRepository orderVsProductRepository;
    @PostMapping("/add")
    public orderResponse addOrder(@RequestBody addOrder addOrder){
        OrderDetailed orderdetailed=new OrderDetailed();
        PortalUser portalUser=portalUserRepo.findById(addOrder.getUserId()).orElse(null);
        orderdetailed.setUser(portalUser);
        orderdetailed.setTotalPrice(addOrder.getTotalPrice());
        orderdetailed.setQuantity(addOrder.getQuantity());
        orderDetailedRepository.save(orderdetailed);
        orderResponse orderResponse=new orderResponse(addOrder.getUserId(),addOrder.getQuantity(),addOrder.getTotalPrice(),orderdetailed.isDelivered());
        return orderResponse;

    }
    @PostMapping("/register")
    public ResponseEntity registerOrder(@RequestBody OrderVsProductReqBody orderVsProductReqBody){
        OrderVSProduct orderVSProduct=new OrderVSProduct();
        orderVSProduct.setOrderId(orderVsProductReqBody.getOrderId());
        orderVSProduct.setProductId(orderVsProductReqBody.getProductId());
        orderVSProduct.setTotalPrice(orderVsProductReqBody.getPrice());
        orderVSProduct.setTotalQuantity(orderVsProductReqBody.getQuantity());
        orderVsProductRepository.save(orderVSProduct);
        return new ResponseEntity(orderVsProductReqBody,HttpStatus.CREATED);
    }
    @GetMapping("/user/details")
    public ResponseEntity getOrderDetailsByOrderId(@RequestParam UUID orderId,@RequestParam UUID userId){
     OrderDetailed orderDetailed=orderDetailedRepository.getOrderByOrderId(orderId,userId);
           if(orderDetailed==null){
               return new ResponseEntity(null,HttpStatus.OK);
           }
        OrderUserDetailedResponseBody orderUserDetailedResponseBody=new OrderUserDetailedResponseBody();
           orderUserDetailedResponseBody.setUserId(userId);
           orderUserDetailedResponseBody.setOrderId(orderDetailed.getId());
           orderUserDetailedResponseBody.setTotalPrice(orderDetailed.getTotalPrice());
           orderUserDetailedResponseBody.setTotalQuantity(orderDetailed.getQuantity());
           return new ResponseEntity(orderUserDetailedResponseBody,HttpStatus.OK);
    }
    @GetMapping("/details")
    public ResponseEntity getOrderVsProductId(@RequestParam UUID orderId){
        List<OrderVSProduct> getAllProduct=orderVsProductRepository.getOrderVsProductId(orderId);
        return new ResponseEntity(getAllProduct,HttpStatus.OK);
    }
}
