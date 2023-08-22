package com.lalith.backendproject.eCommerceApplication.service;

import com.lalith.backendproject.eCommerceApplication.model.Inventory;
import com.lalith.backendproject.eCommerceApplication.model.Order;
import com.lalith.backendproject.eCommerceApplication.model.Product;
import com.lalith.backendproject.eCommerceApplication.repository.InventoryRepository;
import com.lalith.backendproject.eCommerceApplication.repository.OrderRepository;
import com.lalith.backendproject.eCommerceApplication.repository.ProductRepository;
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

    @Autowired
    public ProductRepository productRepository;

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
        for (Product product : order.getInventoryList()) {
            if (product.getProductId() != null) {
                Optional<Inventory> inventoryOptional = inventoryRepository.findById(product.getProductId());
                if (inventoryOptional.isPresent()) {
                    Inventory inventory = inventoryOptional.get();
                    Optional<Product> matchingProductOptional = inventory.getProd().stream()
                            .filter(p -> p.getProductId().equals(product.getProductId()))
                            .findFirst();
                    if (matchingProductOptional.isPresent()) {
                        Product matchingProduct = matchingProductOptional.get();
                        Product updatedProduct = new Product(
                                product.getProductId(),
                                matchingProduct.getProductName(),
                                matchingProduct.getPrice()*product.getQuantity(),
                                matchingProduct.getCategory(),
                                product.getQuantity()
                        );
                        int index = order.getInventoryList().indexOf(product);
                        order.getInventoryList().set(index, updatedProduct);
                    }
                }
            }
            List<Product> addItemtoInventory = new ArrayList<>();
            if (order.getOrderType() == OrderType.purchase) {
                Optional<Inventory> optionalInventory = inventoryRepository.findById(product.getProductId());
                if (optionalInventory.isPresent()) {
                    List<Product> inventory = optionalInventory.get().getProd();
                    for (Product inven : inventory) {
                        int newQuantity = product.getQuantity() + inven.getQuantity();
                        inven.setQuantity(newQuantity);
                        order.setCurrentDateTimeInfo(LocalDateTime.now());
                        orderRepository.save(order);
                        inventoryRepository.save(optionalInventory.get());
                        orders.add(product.getProductId() + " has been purchased and inventory has been updated successfully!");
                    }
                } else {
                    addItemtoInventory.add(product);
                    Inventory addProdtoDB = new Inventory(addItemtoInventory);
                    addProdtoDB.setProductId(product.getProductId());
                    orders.add(product.getProductId() + " Product has been purchased and added to inventory successfully!");
                    inventoryRepository.save(addProdtoDB);
                    productRepository.save(product);
                    orderRepository.save(order);
                }
            }
            order.setCurrentDateTimeInfo(LocalDateTime.now());
            orderRepository.save(order);
            if (order.getOrderType() == OrderType.sale) {
                // If it is sale (sold to customer)
                if (inventoryRepository.existsById(product.getProductId())) {
                    Optional<Inventory> saleInventory = inventoryRepository.findById(product.getProductId());
                    for (Product prod : saleInventory.get().prod) {
                        if (prod.getQuantity() >= product.getQuantity()) {
                            prod.setQuantity(prod.getQuantity() - product.getQuantity());
                            inventoryRepository.save(saleInventory.get());
                            order.setCurrentDateTimeInfo(LocalDateTime.now());
                             orderRepository.save(order);
                            orders.add(prod.getProductId() + " Product has been sold successfully - Quantity : " + product.getQuantity()  );
                        } else {
                            orders.add(prod.getProductId() + " cannot be sold because of insufficient quantity, as you are placing order for a quantity of "+
                                    product.getQuantity() +
                               ". " + "But, we only have a quantity of " + prod.getQuantity() + ".");
                        }
                    }
                } else {
                    orders.add(product.getProductId() + " Product is not in inventory");
                }
            }
        }
        return orders;
    }
}
