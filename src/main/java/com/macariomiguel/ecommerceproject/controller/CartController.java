package com.macariomiguel.ecommerceproject.controller;

import com.macariomiguel.ecommerceproject.dto.CartItemRequestDTO;
import com.macariomiguel.ecommerceproject.dto.CartItemResponseDTO;
import com.macariomiguel.ecommerceproject.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItemResponseDTO> getCartItems() {
        return cartService.showCart();
    }

    @PostMapping
    public ResponseEntity<Void> addCartItem (@RequestBody CartItemRequestDTO data){
        cartService.addCartItem(data.sku(), data.quantity());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{sku}")
                .buildAndExpand(data.sku())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeCartItem(@RequestBody CartItemRequestDTO data){
        cartService.deleteCartItem(data.sku(), data.quantity());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateCartItem(@RequestBody CartItemRequestDTO data){
        cartService.updateCartItemQuantity(data.sku(), data.quantity());
        return ResponseEntity.ok().build();
    }

}
