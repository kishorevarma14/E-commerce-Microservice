package com.example.E_commerce_cart.Repository;

import com.example.E_commerce_cart.Product.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<product,Integer> {
//    Optional<product> findByProductName(String productname);
}
