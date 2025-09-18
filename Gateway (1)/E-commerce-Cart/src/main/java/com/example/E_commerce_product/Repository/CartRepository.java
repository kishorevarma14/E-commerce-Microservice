package com.example.E_commerce_product.Repository;

import com.example.E_commerce_product.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByproductName(String product_name);
    @Query(value="Select * from Cart where username=:username",nativeQuery = true)
    List<Cart> findCartsforUser(String username);
}
