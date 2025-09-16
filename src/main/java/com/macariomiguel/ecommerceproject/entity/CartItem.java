package com.macariomiguel.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @EqualsAndHashCode.Include
    private Product product;

    private String name;
    private Integer quantity;
    private Double price;

    @Column(unique = true)
    private String SKU;

    public CartItem(Product product, Integer quantity) {
        this.name = product.getName();
        this.quantity = quantity;
        this.price = product.getPrice();
        this.product = product;
        this.SKU = product.getSku();
    }



}
