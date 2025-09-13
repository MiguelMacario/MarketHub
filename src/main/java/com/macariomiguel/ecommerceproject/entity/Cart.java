package com.macariomiguel.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Product product;

    @OneToOne
    private Order order;

    @OneToMany
    private List<CartItem> cartItems;

    private Double totalPrice;

    private void addCartItem(Product product, Integer quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
    }


}
