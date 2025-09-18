package com.example.E_commerce_cart.productService;

import com.example.E_commerce_cart.Feign;
import com.example.E_commerce_cart.Product.ProductDto;
import com.example.E_commerce_cart.Product.product;
import com.example.E_commerce_cart.Repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    HttpServletRequest request;
    @Autowired
    Feign feign;
    public List<product> getallproducts() {
        return productRepository.findAll();
    }

    public String addcart(int id) {
        Optional<product> existing=productRepository.findById(id);
        if(existing.isPresent())
        {
            product newone=existing.get();
            ProductDto productDto=new ProductDto();
            productDto.setProductName(newone.getProductName());
            productDto.setProductPrice(newone.getProductPrice());
            productDto.setQuantity(newone.getQuantity());
            System.out.println(productDto);
            return feign.createcart(productDto,request.getHeader("Authorization"));
        }
        return "Product not available";
    }

    public String createproduct(product Product) {
        productRepository.save(Product);
        return "saved";
    }
//
//    public String updateproduct(product Product) {
//        Optional<product> product1=productRepository.findByProductName(Product.getProductName());
//        if(product1.isPresent())
//        {
//            product existing=new product();
//            existing.setProductName(Product.getProductName());
//            existing.setProductPrice(Product.getProductPrice());
//            existing.setSpecs(Product.getDeliveryTime());
//            existing.setDeliveryTime(existing.getSpecs());
//            productRepository.save(existing);
//            return "Saved";
//        }
//        productRepository.save(Product);
//        return "Saved";
//    }
//    public String deleteproduct(int id)
//    {
//        Optional<product> existing=productRepository.findById(id);
//        if(existing.isPresent())
//        {
//            productRepository.deleteById(id);
//            return "Deleted";
//        }
//        return "Not found";
//    }
}
