package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE product_name LIKE %?1%")
    public Page<Product> searchProduct(String keyword, Pageable pageable);
}
