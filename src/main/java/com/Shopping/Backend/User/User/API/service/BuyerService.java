package com.Shopping.Backend.User.User.API.service;

import com.Shopping.Backend.User.User.API.DTO.GeneralUseDTO.UserDTO;
import com.Shopping.Backend.User.User.API.DTO.RequstBody.*;
import com.Shopping.Backend.User.User.API.DTO.ResponseBody.*;
import com.Shopping.Backend.User.User.API.exception.UnAuthorized;
import com.Shopping.Backend.User.User.API.exception.UnavailableException;
import com.Shopping.Backend.User.User.API.utils.ApiUrlUtil;
import com.Shopping.Backend.User.User.API.utils.RequesterUtil;
import com.Shopping.Backend.User.User.API.utils.ShoppingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    RequesterUtil requesterUtil;

    /*
        Will call DB Api one by one to get all products details
        According to every product we will check are we able to place order or not
        If any product is having the less quantity our api will provide response accordingly
     */
    public UserDTO getUser(UUID userId){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://localhost:8080/db/user/getuser?id=" + userId.toString();
        ShoppingLogger.logger.info("Calling DB API to get user details");
        ResponseEntity<UserDTO> userResp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UserDTO.class);
         return userResp.getBody();
    }
    public MailResponseDto sedMail(OrderMailDTO orderMailDTO){
        HttpHeaders httpHeaders=requesterUtil.getHeader();
        HttpEntity httpEntity=requesterUtil.createHttpEntity(orderMailDTO,httpHeaders);
        RestTemplate restTemplate=requesterUtil.getRestTemplate();
        String url=ApiUrlUtil.mailApiURL+"/buyer/buyeProduct";
             ResponseEntity<MailResponseDto> respMail= restTemplate.exchange(url,HttpMethod.POST,httpEntity, MailResponseDto.class);
            return respMail.getBody();
    }
    public Bill orderProduct(OrderDto orderDto)  {
        ShoppingLogger.logger.info("Inside Service Layer will call db api to get all product details");
        List<OrderProductDto> orderProducts = orderDto.getOrderProductDtoList();//user
        UserDTO user=getUser(orderDto.getUserId());
        int totalPrice = 0;
        int totalQuantity = 0;
        //database
        List<ProductResponseBody> products = new ArrayList<>();
        for (OrderProductDto orderProductDto : orderProducts) {
            UUID productId = orderProductDto.getProductId();
            // We will call db api to get specific product details
            String url = ApiUrlUtil.dbApiURL + "/product/get" + "?productId=" + productId.toString();
            RestTemplate requestor = requesterUtil.getRestTemplate();
            HttpHeaders header = requesterUtil.getHeader();
            HttpEntity httpEntity = requesterUtil.createHttpEntity(header);
            ResponseEntity<ProductResponseBody> product = requestor.exchange(url, HttpMethod.GET, httpEntity, ProductResponseBody.class);
            ProductResponseBody pBody = product.getBody();
            if (pBody.getQuantity() < orderProductDto.getQuantity()) {
               throw new UnavailableException(String.format("Product %s is having less Quntity %d",pBody.getProductName(), pBody.getQuantity()));
            }
            totalQuantity += orderProductDto.getQuantity();
            totalPrice += pBody.getPrice() * orderProductDto.getQuantity();
            products.add(product.getBody());
        }
        ShoppingLogger.logger.info("Got all the products present in the system");
        //Save db
        String orderUrl = ApiUrlUtil.dbApiURL + "/order/add";
        AddOrderRequestBody addOrderRequestBody = new AddOrderRequestBody(orderDto.getUserId(), totalQuantity, totalPrice);
        HttpHeaders orderHeader = requesterUtil.getHeader();
        HttpEntity httpEntity = requesterUtil.createHttpEntity(addOrderRequestBody, orderHeader);
        RestTemplate orderRequster = requesterUtil.getRestTemplate();
        ResponseEntity<OrderDetailedResponseBody> orderResp = orderRequster.exchange(orderUrl, HttpMethod.POST, httpEntity, OrderDetailedResponseBody.class);
        OrderDetailedResponseBody order = orderResp.getBody();
        //ShoppingLogger.logger.info("Got all the products save in the system"+orderResp.getBody());
        //update orderVsproduct table
        List<BillProductDetailsDTO> productList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            int userQuantity = orderProducts.get(i).getQuantity();
            int dBQuantity = products.get(i).getQuantity();
            UUID orderId = order.getId();
            int price = userQuantity * products.get(i).getPrice();
            UUID productId = products.get(i).getProductId() ;
            OrderVsProductReqBody orderVsProductReqBody = new OrderVsProductReqBody(orderId, productId, price, userQuantity);
            String opUrl = ApiUrlUtil.dbApiURL + "/order/register";
            HttpEntity opEntity = requesterUtil.createHttpEntity(orderVsProductReqBody, orderHeader);
            RestTemplate opRequst = requesterUtil.getRestTemplate();
            ResponseEntity<OrderVsProductReqBody> opResponse = opRequst.exchange(opUrl, HttpMethod.POST, opEntity, OrderVsProductReqBody.class);
            BillProductDetailsDTO billProductDetailsDTO = new BillProductDetailsDTO(productId, products.get(i).getProductName(), userQuantity, price);
            productList.add(billProductDetailsDTO);
            //update product quantity
            ShoppingLogger.logger.info("Got all the products update quantity in the system");
            int updateQuantity = dBQuantity - userQuantity;
            HttpHeaders orderHeader1 = requesterUtil.getHeader();
            String proDbUrl = ApiUrlUtil.dbApiURL + "/product/update" +"?"+ "productId=" + productId+ "&quantity=" + updateQuantity;
            HttpEntity edf = requesterUtil.createHttpEntity(orderHeader1);
            ResponseEntity res = opRequst.exchange(proDbUrl, HttpMethod.POST, edf,Object.class);

        }
        ShoppingLogger.logger.info("Got all the products update in the system");
        Bill bill = new Bill(order.getId(), productList, totalQuantity, totalPrice);
        //Send maill
        String mailText=generateMessage(productList,order.getId(),totalQuantity,totalPrice,user);
           OrderMailDTO mail=new OrderMailDTO(user.getEmail(),mailText,"Congratulation !! your order are place");
           sedMail(mail);
        return bill;
    }
    public String generateMessage(List<BillProductDetailsDTO> productList,UUID orderId,int totalQuantity, int totalPrice, UserDTO userDTO){
        String message=String.format("Hii %s\n" +
                "Congratulation !! Your order got placed in Below is your order details:\n",userDTO.getName());
               message+=String.format("Count,ProductName,ProductQuantity,Price:\n");
               int count=0;
               for(BillProductDetailsDTO billProductDetailsDTO:productList){
                   String productTex=String.format("%d %s %d %d:\n",count,billProductDetailsDTO.getProductName(),billProductDetailsDTO.getQuantity(),billProductDetailsDTO.getPrice());
                   message+=productTex;
                   count++;
               }
               message+=String.format("TotalQuantity:%d :\n",totalQuantity);
               message+=String.format("TotalPrice: %d :\n",totalPrice);
               message+=String.format("Happy Shopping:\n"+"AccioShopping Team");
               return message;
    }
    public Object getOrderById(UUID userId,UUID orderId){
        HttpHeaders httpHeaders=requesterUtil.getHeader();
        HttpEntity httpEntity=requesterUtil.createHttpEntity(httpHeaders);
        RestTemplate restTemplate=requesterUtil.getRestTemplate();
        String url=ApiUrlUtil.dbApiURL+"/order/user/details"+"?"+"userID="+userId+"&orderId="+orderId;
        ResponseEntity<OrderResponseBody> responseOrder= restTemplate.exchange(url,HttpMethod.GET,httpEntity, OrderResponseBody.class);
       if(responseOrder.getBody()==null){
      throw new UnAuthorized("User are not unauthorised to by product");
       }
        RestTemplate restTemplate1=requesterUtil.getRestTemplate();
        String urlOrder=ApiUrlUtil.dbApiURL+"/order/details?"+"orderId"+orderId.toString();
        ResponseEntity responseDetails= restTemplate1.exchange(urlOrder,HttpMethod.GET,httpEntity,OrderVSProduct.class);
         Object orderProduct=responseOrder.getBody();
         return orderProduct;
    }
}

