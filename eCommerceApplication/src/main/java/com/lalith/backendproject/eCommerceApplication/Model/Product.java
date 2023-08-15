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
    private int quantity;
}
