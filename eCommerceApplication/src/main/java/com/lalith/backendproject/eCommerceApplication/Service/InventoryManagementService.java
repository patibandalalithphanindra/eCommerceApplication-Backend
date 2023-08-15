package com.lalith.backendproject.eCommerceApplication.Service;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
import com.lalith.backendproject.eCommerceApplication.Model.Product;
import com.lalith.backendproject.eCommerceApplication.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryManagementService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryManagementService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    //Getting, Updating, Deleting Items in the inventory

    // 1) Getting all products in the inventory
    public List<Inventory> getAllProducts() {
        return inventoryRepository.findAll();
    }

    // 2) Deleting a product in the inventory
    public String deleteProduct(String productId) {
        if (inventoryRepository.existsById(productId)) {
            inventoryRepository.deleteById(productId);
            return productId + " Product has been deleted Successfully!";
        } else {
            return productId + " is not present in the inventory, Hence it can't be deleted";
        }
    }

    //3) Updating the product in the inventory
    public String updateProduct(String productId, Product updatedProduct) {
        Optional<Inventory> existingProduct = inventoryRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Product does not exist");
        } else {
            Inventory inventory = existingProduct.get();
            for (Product prod : inventory.getProd()) {
                if (updatedProduct.getProductName() != null) {
                    prod.setProductName(updatedProduct.getProductName());
                }
                if (updatedProduct.getQuantity() != 0) {
                    prod.setQuantity(updatedProduct.getQuantity());
                }
                if (updatedProduct.getPrice() != 0.0) {
                    prod.setPrice(updatedProduct.getPrice());
                }
                if (updatedProduct.getCategory() != null) {
                    prod.setCategory(updatedProduct.getCategory());
                }
                if (updatedProduct.getProductId() != null) {
                    prod.setProductId(updatedProduct.getProductId());
                }
                //saving the updated product in the inventory repository
                inventoryRepository.save(inventory);
            }
        }
        return productId + " has been updated successfully!";
    }
}


