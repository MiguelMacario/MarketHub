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

    public void addCartItem(String sku, Integer quantity) {
        Product product = existingProduct(sku);

        User currentUser = auth.currentUser();
        Cart cart = cartRepository.findByUser_Id(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem existingItem = existingCartItem(cart, product);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cart.addCartItems(new CartItem(product, quantity));
        }

        cartRepository.save(cart);
    }

    public List<CartItemResponseDTO> showCart() {
        Cart cart = userCart();

        return cart.getCartItems()
                .stream()
                .map(CartItemResponseDTO :: new)
                .collect(Collectors.toList());
    }


    public void deleteCartItem(String sku, Integer quantity) {
        Product product = existingProduct(sku);

        Cart cart = userCart();

        CartItem existingItem = existingCartItem(cart, product);

        if (existingItem == null) {
            throw new ResourceNotFoundException("Item not found in cart");
        }

        if (quantity == null || quantity >= existingItem.getQuantity()) {
            cart.getCartItems().remove(existingItem);
        } else {
            existingItem.setQuantity(existingItem.getQuantity() - quantity);
        }

        cartRepository.save(cart);
    }

    private void updateCartItemQuantity(String sku, Integer newQuantity){
        Product product = existingProduct(sku);

        Cart cart = userCart();

        CartItem existingItem = existingCartItem(cart, product);

        if (existingItem == null) {
            throw new ResourceNotFoundException("Item not found in cart");
        }

        existingItem.setQuantity(newQuantity);
        cartRepository.save(cart);
    }

    private CartItem existingCartItem(Cart cart, Product product) {
        return cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getSku().equals(product.getSku()))
                .findFirst()
                .orElse(null);
    }

    private Product existingProduct(String sku) {
        return productRepository.findBySkuIgnoreCase(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private User currentUser() {
        return auth.currentUser();
    }

    private Cart userCart() {
        return cartRepository.findByUser_Id(currentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }


}
