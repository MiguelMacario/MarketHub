package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
