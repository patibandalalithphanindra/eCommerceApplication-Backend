package com.lalith.backendproject.eCommerceApplication.Repository;

import com.lalith.backendproject.eCommerceApplication.Model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory,String> {
 }
