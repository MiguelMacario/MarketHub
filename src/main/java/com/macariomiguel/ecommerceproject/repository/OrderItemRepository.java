package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
