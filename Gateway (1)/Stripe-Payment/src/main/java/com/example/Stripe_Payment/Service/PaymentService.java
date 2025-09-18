package com.example.Stripe_Payment.Service;

import com.example.Stripe_Payment.DTO.ProductDto;
import com.example.Stripe_Payment.DTO.ResponseDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
    @Value("${stripe.ApiKey}")
    private String apikey;
    public ResponseDTO paymentsession(ProductDto productDto) {
        Stripe.apiKey=apikey;
        SessionCreateParams.LineItem.PriceData.ProductData ProductData = SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(productDto.getProductName()).build();
        SessionCreateParams.LineItem.PriceData PriceData = SessionCreateParams.LineItem.PriceData.builder().setCurrency("USD").setUnitAmount(productDto.getProductPrice()*100).setProductData(ProductData).build();
        SessionCreateParams.LineItem Lineitem = SessionCreateParams.LineItem.builder().setQuantity(productDto.getQuantity()).setPriceData(PriceData).build();
        SessionCreateParams params=SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl("http://localhost:8080/Sucess").setCancelUrl("http://localhost:8080/Failure").addLineItem(Lineitem).build();
        Session session=null;
        try {
            session=Session.create(params);
        } catch (StripeException e) {
            throw new RuntimeException("Session Not Created");
        }
        return ResponseDTO.builder().sessionId(session.getId()).sessionUrl(session.getUrl()).build();
    }
}
