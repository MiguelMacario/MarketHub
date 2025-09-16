package com.macariomiguel.ecommerceproject.controller;

import com.macariomiguel.ecommerceproject.dto.CartItemRequestDTO;
import com.macariomiguel.ecommerceproject.dto.CartItemResponseDTO;
import com.macariomiguel.ecommerceproject.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartItemResponseDTO> getCartItems() {
        cartService.showCart();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addCartItem (@RequestBody CartItemRequestDTO data){
        cartService.addCartItem(data.sku(), data.quantity());
        return ResponseEntity.ok().build();
    }

}
