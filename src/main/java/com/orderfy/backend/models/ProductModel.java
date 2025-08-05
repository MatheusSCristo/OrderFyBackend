package com.orderfy.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductModel extends AbstractModel {

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryModel> categories;
    private String name;
    @Column(columnDefinition ="TEXT")
    private String description;
    private BigDecimal price;
    @Column(name = "image_url")
    private String imageUrl;
    private boolean available;
    @ManyToOne(fetch=FetchType.LAZY)
    private RestaurantModel restaurant;
}
