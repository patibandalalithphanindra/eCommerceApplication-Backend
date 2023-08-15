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
    public Inventory(List<Product> prod) {
        this.prod = prod;
    }

    //    public Inventory(int quantity) {
//        this.quantity = quantity;
//    }
    public List<Product> prod;


}
