package com.example.Order_Service.Repository;

import com.example.Order_Service.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(String name);
}
