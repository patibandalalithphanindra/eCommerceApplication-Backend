package com.lalith.backendproject.eCommerceApplication.repository;

import com.lalith.backendproject.eCommerceApplication.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory,String> {
 }
