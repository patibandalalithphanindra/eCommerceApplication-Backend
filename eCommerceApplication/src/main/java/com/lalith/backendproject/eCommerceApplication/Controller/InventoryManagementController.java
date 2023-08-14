package com.lalith.backendproject.eCommerceApplication.Controller;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
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

    // 2) Add a product to Inventory
    @PostMapping("/add")
    public List<String> addInventory(@RequestBody List<Inventory> inventory){
        return inventoryManagementService.addProduct(inventory);
    }

    // 3) Delete a product in inventory
    @DeleteMapping("/{productId}")
    public String deleteInventory(@PathVariable("productId") String productId){
        return inventoryManagementService.deleteProduct(productId);
    }

    // 4) Update the inventory
    @PutMapping("/{productId}")
    public String updateInventory(@PathVariable("productId") String productId, @RequestBody Inventory inventory){
        return inventoryManagementService.updateProduct(productId,inventory);
    }

}
