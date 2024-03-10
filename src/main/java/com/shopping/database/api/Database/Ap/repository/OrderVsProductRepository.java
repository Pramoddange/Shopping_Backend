package com.shopping.database.api.Database.Ap.repository;

import com.shopping.database.api.Database.Ap.model.OrderDetailed;
import com.shopping.database.api.Database.Ap.model.OrderVSProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderVsProductRepository extends JpaRepository<OrderVSProduct, UUID> {
    @Query( value= "select * from order_VS_Product where order_id=:orderId",nativeQuery = true)
    public List<OrderVSProduct> getOrderVsProductId(UUID orderId);
}
