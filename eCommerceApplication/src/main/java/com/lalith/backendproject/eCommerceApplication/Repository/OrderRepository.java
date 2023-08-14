package com.lalith.backendproject.eCommerceApplication.Repository;

import com.lalith.backendproject.eCommerceApplication.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
