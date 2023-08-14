package com.lalith.backendproject.eCommerceApplication.Service;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
import com.lalith.backendproject.eCommerceApplication.Model.Order;
import com.lalith.backendproject.eCommerceApplication.Repository.InventoryRepository;
import com.lalith.backendproject.eCommerceApplication.Repository.OrderRepository;
import com.lalith.backendproject.eCommerceApplication.type.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManagementService {

    @Autowired
    public OrderRepository orderRepository;
  @Autowired
   public InventoryRepository inventoryRepository;

  // 1) Finding All Orders
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    // 2) Count of Orders
    public int findOrderCount(){
        return (int)orderRepository.count();
    }

    // 3) Adding Order Details
    public List<String> addOrder(List<Order> orderList){
        List<String> orders = new ArrayList<>();

        for(Order order : orderList){
            // If it is Purchase (to stock up the inventory)
            if(order.getOrderType() == OrderType.purchase){
                if(inventoryRepository.existsById(order.getProductId())){
                    Optional<Inventory> purchaseInventory = inventoryRepository.findById(order.getProductId());
                    order.setProductName(purchaseInventory.get().getProductName());
                    purchaseInventory.get().setQuantity(order.getQuantity() + purchaseInventory.get().getQuantity());
                    inventoryRepository.save(purchaseInventory.get());
                    order.setCurrentDateTimeInfo(LocalDateTime.now());
                    orderRepository.save(order);
                    orders.add(order.getProductId() + " has been purchased and added to the inventory successfully!");
                } else {
                    orders.add(order.getProductId() + "Product is not in inventory");
                }
            } else {
                // If it is sale (sold to customer)
                if(inventoryRepository.existsById(order.getProductId())){
                  Optional<Inventory> saleInventory = inventoryRepository.findById(order.getProductId());
                  if(order.getQuantity() <= saleInventory.get().getQuantity()){
                      saleInventory.get().setQuantity(saleInventory.get().getQuantity() - order.getQuantity());
                      inventoryRepository.save(saleInventory.get());
                      order.setCurrentDateTimeInfo(LocalDateTime.now());
                      orderRepository.save(order);
                      orders.add(order.getProductId() + " Product has been sold successfully!");
                  }
                  else {
                      orders.add(order.getProductId() + " has not been sold because of unavailability");
                  }
                } else {
                    orders.add(order.getProductId() + " Product is not in inventory");
                }
            }
        }
       return orders;
    }
}
