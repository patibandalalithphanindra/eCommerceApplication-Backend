package com.lalith.backendproject.eCommerceApplication;

import com.lalith.backendproject.eCommerceApplication.model.Inventory;
import com.lalith.backendproject.eCommerceApplication.model.Order;
import com.lalith.backendproject.eCommerceApplication.model.Product;
import com.lalith.backendproject.eCommerceApplication.repository.InventoryRepository;
import com.lalith.backendproject.eCommerceApplication.repository.OrderRepository;
import com.lalith.backendproject.eCommerceApplication.repository.ProductRepository;
import com.lalith.backendproject.eCommerceApplication.service.InventoryManagementService;
import com.lalith.backendproject.eCommerceApplication.service.OrderManagementService;
import com.lalith.backendproject.eCommerceApplication.service.ProductManagementService;
import com.lalith.backendproject.eCommerceApplication.type.OrderType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ServiceTest.class})
public class ServiceTest {
    @Mock
    InventoryRepository inventoryRepository;
    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    InventoryManagementService inventoryManagementService;
    @InjectMocks
    OrderManagementService orderManagementService;

    @InjectMocks
    ProductManagementService productManagementService;

    @Test
    public void test_getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Product 1", 10.0, "Category 1", 100));
        products.add(new Product("P2", "Product 2", 20.0, "Category 2", 200));

        Inventory inventory = new Inventory(products);

        when(inventoryRepository.findAll()).thenReturn(List.of(inventory));

        List<Inventory> result = inventoryManagementService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(products.size(), result.get(0).getProd().size());
    }

    @Test
    public void test_deleteExistingProduct() {
        String productIdToDelete = "P5";
        when(inventoryRepository.existsById(productIdToDelete)).thenReturn(true);
        String result = inventoryManagementService.deleteProduct(productIdToDelete);
        verify(inventoryRepository, times(1)).deleteById(productIdToDelete);
        assertEquals(productIdToDelete + " Product has been deleted Successfully!", result);
    }

    @Test
    public void test_deleteNotExistingProduct() {
        String productIdToDelete = "P10";
        when(inventoryRepository.existsById(productIdToDelete)).thenReturn(false);
        String result = inventoryManagementService.deleteProduct(productIdToDelete);
        assertEquals(productIdToDelete + " is not present in the inventory, Hence it can't be deleted", result);
    }

    @Test
    public void test_updateProduct() {
        String productIdToUpdate = "P1";

        Product updatedProduct = new Product(productIdToUpdate, "Updated Product", 15.0, "Updated Category", 150);

        List<Product> products = new ArrayList<>();
        products.add(new Product(productIdToUpdate, "Product 1", 10.0, "Category 1", 100));
        Inventory inventory = new Inventory(products);

        when(inventoryRepository.findById(productIdToUpdate)).thenReturn(Optional.of(inventory));

        String result = inventoryManagementService.updateProduct(productIdToUpdate, updatedProduct);

        verify(inventoryRepository, times(1)).findById(productIdToUpdate);
        verify(inventoryRepository, times(1)).save(any(Inventory.class));

        assertEquals(productIdToUpdate + " has been updated successfully!", result);
    }

    @Test
    public void test_addOrder() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("P1", "Product 1", 10.0, "Category 1", 100));
        productList.add(new Product("P2", "Product 2", 20.0, "Category 2", 200));

        Order order = new Order(productList, OrderType.purchase);

        when(orderRepository.save(any())).thenReturn(order);

        List<String> result = orderManagementService.addOrder(order);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void test_findAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("O1", new ArrayList<>(), OrderType.purchase, LocalDateTime.now()));
        orders.add(new Order("O2", new ArrayList<>(), OrderType.sale, LocalDateTime.now()));

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderManagementService.findAllOrders();

        assertEquals(orders.size(), result.size());
    }

    @Test
    public void test_addOrderPurchase() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Product 1", 10.0, "Category 1", 100));
        products.add(new Product("P2", "Product 2", 20.0, "Category 2", 200));

        Order order = new Order(products, OrderType.purchase);
        LocalDateTime currentDateTimeInfo = LocalDateTime.now();
        order.setCurrentDateTimeInfo(currentDateTimeInfo);

        Inventory inventory = new Inventory(products);
        when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));

        List<String> result = orderManagementService.addOrder(order);

        assertNotNull(result);

        assertEquals(2, inventory.prod.size());

    }

    @Test
    public void test_addOrderSale() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Product 1", 10.0, "Category 1", 100));
        products.add(new Product("P2", "Product 2", 20.0, "Category 2", 200));

        Order order = new Order(products, OrderType.sale);
        LocalDateTime currentDateTimeInfo = LocalDateTime.now();
        order.setCurrentDateTimeInfo(currentDateTimeInfo);

        Inventory inventory = new Inventory(products);
        when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));

        List<String> result = orderManagementService.addOrder(order);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
