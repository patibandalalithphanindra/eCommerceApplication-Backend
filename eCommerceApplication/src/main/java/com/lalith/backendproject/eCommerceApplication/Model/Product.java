package com.lalith.backendproject.eCommerceApplication.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String productId;
    private String productName;
    private double price;
    private String category;

    public Product(String productId, String productName, double price, String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    private int quantity;

}
