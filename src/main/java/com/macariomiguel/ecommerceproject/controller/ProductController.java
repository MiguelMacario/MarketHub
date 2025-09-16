package com.macariomiguel.ecommerceproject.controller;

import com.macariomiguel.ecommerceproject.dto.ProductRequestDTO;
import com.macariomiguel.ecommerceproject.dto.ProductResponseDTO;
import com.macariomiguel.ecommerceproject.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDTO> findAll(){
        return productService.findAll();
    }

    @GetMapping("name/{name}")
    public List<ProductResponseDTO> findByName(@PathVariable String name){
        return productService.findByName(name);
    }

    @GetMapping("category/{id}")
    public List<ProductResponseDTO> findByCategory(@PathVariable Long id){
        return productService.findByCategoryId(id);
    }

    @GetMapping("price")
    public List<ProductResponseDTO> findByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice){
        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        productService.createProduct(productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{sku}")
    public ResponseEntity<Void> updateProduct(@PathVariable String sku, @RequestBody ProductRequestDTO data){
        productService.updateProduct(data, sku);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku){
        productService.deleteProduct(sku);
        return ResponseEntity.ok().build();
    }

}
