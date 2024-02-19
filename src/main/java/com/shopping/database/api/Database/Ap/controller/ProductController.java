package com.shopping.database.api.Database.Ap.controller;

import com.shopping.database.api.Database.Ap.DTO.ProductReqBody;
import com.shopping.database.api.Database.Ap.model.PortalUser;
import com.shopping.database.api.Database.Ap.model.Product;
import com.shopping.database.api.Database.Ap.repository.PortalUserRepo;
import com.shopping.database.api.Database.Ap.repository.ProductRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PortalUserRepo portalUserRepo;
    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductReqBody product){
       UUID id=UUID.randomUUID();
        Product p=new Product();
       p.setId(id);
       p.setProductName(product.getProductName());
       p.setQuantity(product.getQuantity());
       p.setPrice(product.getPrice());
       p.setRating(0);
       PortalUser seller=portalUserRepo.findById(product.getSellerId()).orElse(null);
       p.setSeller(seller);
        productRepo.save(p);
       return p;
    }
}
