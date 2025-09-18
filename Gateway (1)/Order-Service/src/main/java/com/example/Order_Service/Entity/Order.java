package com.example.Order_Service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private Integer OrderId;
    private String OrderStatus;
    private String UserId;
}
