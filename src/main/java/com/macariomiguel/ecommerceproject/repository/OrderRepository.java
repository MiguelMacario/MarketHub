package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
