package com.example.E_commerce_product.Cart_service;

import com.example.E_commerce_product.Cart.Cart;
import com.example.E_commerce_product.Cart.ProductDto;
import com.example.E_commerce_product.Cart.ResponseDTO;
import com.example.E_commerce_product.Feign.Feign;
import com.example.E_commerce_product.JwtUtil.JwtUsername;
import com.example.E_commerce_product.Repository.CartRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    Feign feign;
    @Autowired
    HttpServletRequest Request;
    public String createcart(ProductDto productDto,String token) {
        Optional<Cart> existing=cartRepository.findByproductName(productDto.getProductName());
        if(existing.isPresent())
        {
            Cart object=existing.get();
            object.setQuantity(object.getQuantity()+1);
            return "Added "+object.getQuantity()+"items to the cart";
        }
        Cart newone=new Cart();
        newone.setProductName(productDto.getProductName());
        newone.setQuantity(productDto.getQuantity());
        newone.setUsername(getUsername(token));
        System.out.println(getUsername(token));
        newone.setProductPrice(productDto.getProductPrice());
        cartRepository.save(newone);
        return "Added to cart";
    }
    public String deletecart(int id) {
        Optional<Cart> cart=cartRepository.findById(id);
        if(cart.isPresent())
        {
            cartRepository.delete(cart.get());
            return "deleted";
        }
        return "Not Found";
    }
    public ResponseDTO paymentCheckout()
    {
        List<Cart> Total_Carts=cartRepository.findAll();
        Long amount=0L;
        StringBuilder string=new StringBuilder();
        for (Cart totalCart : Total_Carts) {
            amount += (totalCart.getProductPrice() * totalCart.getQuantity());
            string.append(totalCart.getProductName()).append("+");
        }
        ProductDto details_for_payment = ProductDto.builder().productName(string.toString()).productPrice(amount).Quantity(1L).build();
        return feign.checkout(details_for_payment);
    }
    }
}
