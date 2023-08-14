package com.lalith.backendproject.eCommerceApplication.Service;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
import com.lalith.backendproject.eCommerceApplication.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryManagementService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryManagementService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    //Adding, Getting, Updating, Deleting Items in the inventory

    // 1) Getting all products in the inventory
    public List<Inventory> getAllProducts(){
        return inventoryRepository.findAll();
    }

    // 2) Adding a new product to inventory
    public List<String> addProduct(List<Inventory> inventoryList){
        List<String> updatedProductList = new ArrayList<>();

        for(Inventory product : inventoryList){
            if(inventoryRepository.existsById(product.getProductId())){
                updatedProductList.add("Product already exists, Product Id : " + product.getProductId());
            } else{
                Inventory updatedProductInfo = inventoryRepository.save(product);
                updatedProductList.add("Product has been saved successfully!, Product Id : " + updatedProductInfo.getProductId());
            }
        }
        return updatedProductList;
    }

    // 3) Deleting a product in the inventory
    public String deleteProduct(String productId){
              if(inventoryRepository.existsById(productId)){
                  inventoryRepository.deleteById(productId);
                  return productId + " Product has been deleted Successfully!";
              }
              else {
                  return productId + " is not present in the inventory, Hence it can't be deleted";
              }
    }

    //4) Updating the product in the inventory
    public String updateProduct(String productId, Inventory inventory){
        Optional<Inventory> existingProduct = inventoryRepository.findById(productId);
        if(existingProduct.isEmpty()){
            throw new RuntimeException("Product does not exist");
        }
        else{
            existingProduct.get().setProductName(inventory.getProductName());
            existingProduct.get().setCategory(inventory.getCategory());
            existingProduct.get().setPrice(inventory.getPrice());
            existingProduct.get().setQuantity(inventory.getQuantity());
            //saving the updated product in the inventory repository
            inventoryRepository.save(existingProduct.get());
        }
        return productId + " has been updated successfully!";
    }

}
