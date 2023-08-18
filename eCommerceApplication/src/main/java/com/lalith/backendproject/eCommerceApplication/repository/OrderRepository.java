package com.lalith.backendproject.eCommerceApplication.repository;

import com.lalith.backendproject.eCommerceApplication.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
