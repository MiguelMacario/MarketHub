package com.macariomiguel.ecommerceproject.entity;


import com.macariomiguel.ecommerceproject.dto.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;

    @Column(unique = true)
    private String sku;

    @ManyToOne
    @JoinColumn(name="categories_id")
    private Category category;

    public Product(ProductRequestDTO data, Category category) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.sku = data.sku();
        this.category = category;
    }

}
