package com.example.Stripe_Payment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private Long productPrice;
    private Long Quantity;
}
