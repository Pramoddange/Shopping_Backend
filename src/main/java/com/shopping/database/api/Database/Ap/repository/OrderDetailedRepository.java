package com.shopping.database.api.Database.Ap.repository;

import com.shopping.database.api.Database.Ap.model.OrderDetailed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderDetailedRepository extends JpaRepository<OrderDetailed, UUID> {
    @Query( value = "select * from order_detailed where user_id=:userId and id =:orderId",nativeQuery = true)
    public OrderDetailed getOrderByOrderId(UUID orderId,UUID userId);
}
