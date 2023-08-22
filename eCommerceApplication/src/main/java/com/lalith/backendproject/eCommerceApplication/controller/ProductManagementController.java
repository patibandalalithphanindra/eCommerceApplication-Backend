package com.lalith.backendproject.eCommerceApplication.controller;

import com.lalith.backendproject.eCommerceApplication.model.Inventory;
import com.lalith.backendproject.eCommerceApplication.model.Product;
import com.lalith.backendproject.eCommerceApplication.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductManagementController {
    @Autowired
    private ProductManagementService productManagementService;
    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return productManagementService.getAllProducts();
    }
}
