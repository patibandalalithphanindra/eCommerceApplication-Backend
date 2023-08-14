package com.lalith.backendproject.eCommerceApplication.Model;

import com.lalith.backendproject.eCommerceApplication.type.OrderType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "order")
public class Order {
    @Id
    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private List<Inventory> inventoryList;
    private OrderType orderType;
    private LocalDateTime currentDateTimeInfo;
}
