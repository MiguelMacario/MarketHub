package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByNameIgnoreCase(String name);

}
