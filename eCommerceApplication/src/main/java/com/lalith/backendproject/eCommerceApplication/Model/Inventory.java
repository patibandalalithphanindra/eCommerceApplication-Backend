package com.lalith.backendproject.eCommerceApplication.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "inventory")
public class Inventory {
    @Id
    private String productId;
    @Getter
    private String productName;
    @Getter
    private String category;
    @Getter
    private double price;
    @Getter
    private int quantity;

    public Inventory(String productName, String category, double price, int quantity) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}
