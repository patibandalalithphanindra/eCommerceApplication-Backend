package com.lalith.backendproject.eCommerceApplication.Service;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
import com.lalith.backendproject.eCommerceApplication.Model.Order;
import com.lalith.backendproject.eCommerceApplication.Model.Product;
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
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // 2) Count of Orders
    public int findOrderCount() {
        return (int) orderRepository.count();
    }

    // 3) Adding Order Details
    public List<String> addOrder(Order order) {
        List<String> orders = new ArrayList<>();

//        for(Order order : orderList){


        for (Product product : order.getInventoryList()) {
            Optional<Inventory> optionalInventory = inventoryRepository.findById(product.getProductId());

            if (optionalInventory.isPresent()) {
                List<Product> inventory = optionalInventory.get().getProd();
                for (Product prod : inventory) {
                    if (order.getOrderType() == OrderType.purchase) {
                        int newQuantity = prod.getQuantity() + product.getQuantity();
                        prod.setQuantity(newQuantity);
                        inventoryRepository.save(inventory);
                        orders.add(product.getProductId() + " has been purchased and added to the inventory successfully!");
                    } else {
                        if (product.getQuantity() <= prod.getQuantity()) {
                            int newQuantity = prod.getQuantity() - product.getQuantity();
                            prod.setQuantity(newQuantity);
                            inventoryRepository.save(inventory);
                            orders.add(product.getProductId() + " Product has been sold successfully!");
                        } else {
                            orders.add(product.getProductId() + " has not been sold because of unavailability");
                        }
                    }

            } else {
                orders.add(product.getProductId() + " Product is not in inventory");
            }
        }

        order.setCurrentDateTimeInfo(LocalDateTime.now());
        orderRepository.save(order);
             else{
            // If it is sale (sold to customer)
            if (inventoryRepository.existsById(prod.getProductId())) {
                Optional<Inventory> saleInventory = inventoryRepository.findById(prod.getProductId());
                if (prod.getQuantity() <= inven.getQuantity()) {
                    inven.setQuantity(inven.getQuantity() - prod.getQuantity());
                    inventoryRepository.save(saleInventory.get());
//                      order.setCurrentDateTimeInfo(LocalDateTime.now());
                    orderRepository.save(order);
                    orders.add(prod.getProductId() + " Product has been sold successfully!");
                } else {
                    orders.add(prod.getProductId() + " has not been sold because of unavailability");
                }
            } else {
                orders.add(prod.getProductId() + " Product is not in inventory");
            }
        }
    }
}}
//        }}
        return orders;
        }
        }
