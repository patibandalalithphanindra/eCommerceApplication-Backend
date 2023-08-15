package com.lalith.backendproject.eCommerceApplication.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventory")
public class Inventory {

    @Id
    private String productId;
    public List<Product> prod;

    public Inventory(List<Product> prod) {
        this.prod = prod;
    }
}
