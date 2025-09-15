package com.macariomiguel.ecommerceproject.service;

import com.macariomiguel.ecommerceproject.dto.CartItemResponseDTO;
import com.macariomiguel.ecommerceproject.entity.Cart;
import com.macariomiguel.ecommerceproject.entity.CartItem;
import com.macariomiguel.ecommerceproject.entity.Product;
import com.macariomiguel.ecommerceproject.entity.User;
import com.macariomiguel.ecommerceproject.exceptions.ResourceNotFoundException;
import com.macariomiguel.ecommerceproject.repository.CartRepository;
import com.macariomiguel.ecommerceproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final AuthorizationService auth;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, AuthorizationService auth) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.auth = auth;
    }

    private void addCartItem(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        User currentUser = auth.currentUser();
        Cart cart = cartRepository.findByUser_Id(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem existingItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cart.addCartItems(new CartItem(product, quantity));
        }

        cartRepository.save(cart);
    }

    public List<CartItemResponseDTO> showCart() {
        User currentUser = auth.currentUser();
        Cart cart = cartRepository.findByUser_Id(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        return cart.getCartItems()
                .stream()
                .map(CartItemResponseDTO:: new)
                .collect(Collectors.toList());
    }





}
