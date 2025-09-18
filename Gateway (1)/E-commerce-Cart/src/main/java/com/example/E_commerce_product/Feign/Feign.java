package com.example.E_commerce_product.Feign;

import com.example.E_commerce_product.Cart.ProductDto;
import com.example.E_commerce_product.Cart.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("Stripe-Payment")
public interface Feign {
    @PostMapping("/Payment/Checkout")
    public ResponseDTO checkout(@RequestBody ProductDto productDto);
}
