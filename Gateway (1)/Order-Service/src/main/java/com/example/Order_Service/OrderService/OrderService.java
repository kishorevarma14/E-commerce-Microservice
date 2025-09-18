package com.example.Order_Service.OrderService;

import com.example.Order_Service.Entity.Order;
import com.example.Order_Service.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public List<Order> allproductsbyname(String name) {
        List<Order> orders=orderRepository.findByUserId(name);
        return orders;
    }
    public void changestatus(String status) {
        Order existing=orderRepository.findByUserId();
    }
}
