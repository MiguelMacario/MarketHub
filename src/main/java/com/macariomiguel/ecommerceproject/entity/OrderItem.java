package com.macariomiguel.ecommerceproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;
    private Integer quantity;
    private BigDecimal price;

    private String SKU;

    public OrderItem(CartItem item) {
        this.product = item.getProduct();
        this.name = item.getProduct().getName();
        this.quantity = item.getQuantity();
        this.price = item.getProduct().getPrice();
        this.SKU = item.getProduct().getSku();
    }

}
