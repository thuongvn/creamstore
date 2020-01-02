package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public boolean createProduct(Product product) {
        if(product.getProduct_name()!=null && product.getProduct_name() != "") {
            productRepository.save(product);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product getProductById() {
        return null;
    }
}
