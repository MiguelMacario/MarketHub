package com.macariomiguel.ecommerceproject.service;

import com.macariomiguel.ecommerceproject.dto.ProductRequestDTO;
import com.macariomiguel.ecommerceproject.dto.ProductResponseDTO;
import com.macariomiguel.ecommerceproject.entity.Category;
import com.macariomiguel.ecommerceproject.entity.Product;
import com.macariomiguel.ecommerceproject.exceptions.ResourceNotFoundException;
import com.macariomiguel.ecommerceproject.repository.CategoryRepository;
import com.macariomiguel.ecommerceproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createProduct(ProductRequestDTO data) {
        Category category = categoryRepository.findById(data.category_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Product product = new Product(data, category);
        productRepository.save(product);
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO :: new)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductResponseDTO :: new)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id)
                .stream()
                .map(ProductResponseDTO :: new)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findByPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(ProductResponseDTO :: new)
                .collect(Collectors.toList());
    }

    public void updateProduct(ProductRequestDTO data, String sku) {
        Product product = productRepository.findBySkuIgnoreCase(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with sku: " + sku));
        if (data.name() != null) {
            product.setName(data.name());
        }
        if (data.description() != null) {
            product.setDescription(data.description());
        }
        if (data.price() != null) {
            product.setPrice(data.price());
        }
        if (data.category_id() != null) {
            product.setCategory(categoryRepository.findById(data.category_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + data.category_id())));
        }

        productRepository.save(product);
    }

    public void deleteProduct(String sku) {
        Product product = productRepository.findBySkuIgnoreCase(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with sku: " + sku));
        productRepository.delete(product);
    }

}
