package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);
    Product findByNameIgnoreCase(String name);
    List<Product> findByCategoryId(Long id);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

}
