package com.lalith.backendproject.eCommerceApplication.service;

import com.lalith.backendproject.eCommerceApplication.model.Inventory;
import com.lalith.backendproject.eCommerceApplication.model.Product;
import com.lalith.backendproject.eCommerceApplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagementService {
    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
