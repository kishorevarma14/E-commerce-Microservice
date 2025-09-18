package com.example.E_commerce_product.Controller;

import com.example.E_commerce_product.Cart.Cart;
import com.example.E_commerce_product.Cart.ProductDto;
import com.example.E_commerce_product.Cart.ResponseDTO;
import com.example.E_commerce_product.Cart_service.CartService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Cart")
@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/Create")
    public String createcart(@RequestBody ProductDto productDto,@RequestParam String token)
    {
        System.out.println(token);
        return cartService.createcart(productDto,token);
    }
    @PostMapping("/Checkout")
    public ResponseDTO PaymentGateway()
    {
        return cartService.paymentCheckout();
    }
    @DeleteMapping("/Delete/{id}")
    public String deletecart(@PathVariable int id)
    {
        return cartService.deletecart(id);
    }
//    @GetMapping("/All")
//    public List<Cart> viewallcart()
//    {
//        return cartService.viewcart();
//    }
//    @GetMapping("/Username")
//    public String getUsername()
//    {
//        return cartService.getUsername();
//    }

}
