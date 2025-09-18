package com.example.E_commerce_cart.Controller;

import com.example.E_commerce_cart.Product.product;
import com.example.E_commerce_cart.productService.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/Product")
@RestController
public class productController {
    @Autowired
    productService productservice;
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/Home")
    public List<product> getallproducts()
    {
        return productservice.getallproducts();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/Add/{id}")
    public String addcart(@PathVariable int id)
    {
        return productservice.addcart(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/HelloWorld")
    public String helloworld()
    {
        return "Hello World";
    }
//    @PostMapping("Create/Product")
//    public String createProduct(@RequestBody product Product)
//    {
//        return productservice.createproduct(Product);
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PutMapping("Update/Product")
//    public String updateproduct(@RequestBody product Product)
//    {
//        return productservice.updateproduct(Product);
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @DeleteMapping("Delete/Product/{id}")
//    public String deleteproduct(@PathVariable int id)
//    {
//        return productservice.deleteproduct(id);
//    }
}
