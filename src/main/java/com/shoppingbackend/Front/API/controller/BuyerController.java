package com.shoppingbackend.Front.API.controller;

import com.shoppingbackend.Front.API.DTO.requstDTO.OrderDto;
import com.shoppingbackend.Front.API.DTO.responseDTO.Bill;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class BuyerController {
    @PostMapping("/order")
    public ResponseEntity orderProduct(@RequestBody OrderDto orderDto){

        String urlUser="http://localhost:8086/user/buyer/order";

        HttpHeaders httpHeaders=new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity<>(orderDto,httpHeaders);
        RestTemplate reqBody=new RestTemplate();
        ResponseEntity response=reqBody.exchange(urlUser, HttpMethod.POST,httpEntity, Bill.class);
        return response;

    }
    @GetMapping("/order-details")
    public ResponseEntity getOrderBYId(@RequestParam UUID userId, @RequestParam UUID orderId){
        String url="http://localhost:8086/user/buyer/order_details"+"?"+"userId="+userId+"&orderId="+orderId;
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(httpHeaders);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity responseBody=restTemplate.exchange(url,HttpMethod.GET,httpEntity, Object.class);
        return responseBody;
    }
}
