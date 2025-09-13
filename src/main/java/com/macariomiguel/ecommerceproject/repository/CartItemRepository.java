package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
