package com.shopping.database.api.Database.Ap.controller;

import com.shopping.database.api.Database.Ap.DTO.ProductReqBody;
import com.shopping.database.api.Database.Ap.model.PortalUser;
import com.shopping.database.api.Database.Ap.model.Product;
import com.shopping.database.api.Database.Ap.repository.PortalUserRepo;
import com.shopping.database.api.Database.Ap.repository.ProductRepo;
import com.shopping.database.api.Database.Ap.util.ShoppingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/db/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PortalUserRepo portalUserRepo;
    @PostMapping("/add")
    public ProductReqBody addProduct(@RequestBody ProductReqBody product){

       UUID id=UUID.randomUUID();
        Product p=new Product();
       p.setProductId(id);

       p.setProductName(product.getProductName());
       p.setQuantity(product.getQuantity());
        ShoppingLogger.logger.info(""+p.getQuantity());
       p.setPrice(product.getPrice());
       p.setRating(0);
       PortalUser seller=portalUserRepo.findById(product.getSellerId()).orElse(null);
       p.setSeller(seller);
        productRepo.save(p);
        product.setId(id);
       return product;
    }
    @GetMapping("/get")
    public Product getProduct(@RequestParam UUID productId){
        return productRepo.findById(productId).orElse(null);
    }
    @PostMapping("/update")
    public ResponseEntity updateProduct(@RequestParam UUID productId ,@RequestParam int quantity){
       // Product product=productRepo.findById(productId).orElse(null);
        //product.setQuantity(quantity);
       // productRepo.save(product);
       //return new ResponseEntity( HttpStatus.CREATED);
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setQuantity(quantity);
            productRepo.save(product);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);  // Return 404 if the product is not found
        }
    }
}
