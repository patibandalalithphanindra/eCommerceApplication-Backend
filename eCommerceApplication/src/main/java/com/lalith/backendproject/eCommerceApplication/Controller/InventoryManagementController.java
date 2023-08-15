package com.lalith.backendproject.eCommerceApplication.Controller;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
import com.lalith.backendproject.eCommerceApplication.Model.Product;
import com.lalith.backendproject.eCommerceApplication.Service.InventoryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryManagementController {
    @Autowired
    public InventoryManagementService inventoryManagementService;

    public InventoryManagementController(InventoryManagementService inventoryManagementService) {
        this.inventoryManagementService = inventoryManagementService;
    }

    // 1) Get All Products in the Inventory
    @GetMapping("/getAllProducts")
    public List<Inventory> getInventory(){
        return inventoryManagementService.getAllProducts();
    }

    // 3) Delete a product in inventory
    @DeleteMapping("/{productId}")
    public String deleteInventory(@PathVariable("productId") String productId){
        return inventoryManagementService.deleteProduct(productId);
    }

    // 4) Update the inventory
    @PatchMapping("/{productId}")
    public String updateInventory(@PathVariable("productId") String productId, @RequestBody Product product){
        return inventoryManagementService.updateProduct(productId,product);
    }

}

