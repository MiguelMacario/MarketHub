package com.macariomiguel.ecommerceproject.service;

import com.macariomiguel.ecommerceproject.entity.*;
import com.macariomiguel.ecommerceproject.exceptions.ResourceNotFoundException;
import com.macariomiguel.ecommerceproject.repository.CartRepository;
import com.macariomiguel.ecommerceproject.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AuthorizationService auth;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, AuthorizationService auth) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.auth = auth;
    }

    public void CheckOut(){
        Cart cart = userCart();

        if (cart.getCartItems().isEmpty()){
            throw new ResourceNotFoundException("Cart is empty");
        }

        Order order = new Order();
        order.setCart(cart);
        order.setUser(currentUser());

        for (CartItem item : cart.getCartItems()){
            order.addOrderItem(new OrderItem(item));
        }

        orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    private User currentUser() {
        return auth.currentUser();
    }

    private Cart userCart() {
        return cartRepository.findByUser_Id(currentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }


}
