package com.example.Order_Service.Controller;

import com.example.Order_Service.Entity.Order;
import com.example.Order_Service.OrderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/{name}")
    public List<Order> OrderByName(@PathVariable String name)
    {
        return orderService.allproductsbyname(name);
    }
    @PostMapping("/{status}")
    public void ChangeStatus(@PathVariable String status)
    {
        orderService.changestatus(status);
    }
}
