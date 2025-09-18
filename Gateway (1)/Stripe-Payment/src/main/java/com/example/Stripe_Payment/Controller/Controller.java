package com.example.Stripe_Payment.Controller;

import com.example.Stripe_Payment.DTO.ProductDto;
import com.example.Stripe_Payment.DTO.ResponseDTO;
import com.example.Stripe_Payment.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Payment")
@RestController
public class Controller {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/Checkout")
    public ResponseDTO checkout(@RequestBody ProductDto productDto)
    {
        ResponseDTO responseDTO=paymentService.paymentsession(productDto);
        return responseDTO;
    }
}
