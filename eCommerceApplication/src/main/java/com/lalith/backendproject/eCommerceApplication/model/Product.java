package com.lalith.backendproject.eCommerceApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int quantity;

    public Product(String productId, String productName, double price, String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }
}
