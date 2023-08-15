package com.lalith.backendproject.eCommerceApplication.Controller;

import com.lalith.backendproject.eCommerceApplication.Model.Order;
import com.lalith.backendproject.eCommerceApplication.Service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderManagementController {
    @Autowired
    private OrderManagementService orderManagementService;

    // 1) Get All Orders
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return orderManagementService.findAllOrders();
    }

    // 2) Get Total Count of Orders
    @GetMapping("/getOrderCount")
    public int getTotalOrderCount(){
        return orderManagementService.findOrderCount();
    }

    // 3) Add an Order
    @PostMapping("/addOrder")
    public List<String> addOrder(@RequestBody Order orderList){
     return orderManagementService.addOrder(orderList);
    }
}