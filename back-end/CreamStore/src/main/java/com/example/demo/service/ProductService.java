package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public boolean createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(int id);
    public List<Product> getAllProduct();
    public Product getProductById();
}
