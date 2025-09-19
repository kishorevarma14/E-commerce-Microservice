package com.example.E_commerce_cart.OpenFeign;

import com.example.E_commerce_cart.Product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="E-commerce-product")
public interface Feign {
    @PostMapping("Cart/Create")
    public String createcart(@RequestBody ProductDto productDto, @RequestParam String token);
}
