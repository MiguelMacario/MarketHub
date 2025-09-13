package com.macariomiguel.ecommerceproject.repository;

import com.macariomiguel.ecommerceproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
