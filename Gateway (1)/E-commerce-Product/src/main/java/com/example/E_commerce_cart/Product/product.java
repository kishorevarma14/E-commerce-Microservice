package com.example.E_commerce_cart.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class product {
    @Id
    @GeneratedValue
    private int id;
    private String ProductName;
    private Long ProductPrice;
    private String Specs;
    private String DeliveryTime;
    private Long Quantity;
}
