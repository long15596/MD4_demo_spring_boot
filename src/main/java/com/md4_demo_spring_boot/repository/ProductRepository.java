package com.md4_demo_spring_boot.repository;

import com.md4_demo_spring_boot.model.Category;
import com.md4_demo_spring_boot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContaining(Pageable pageable, String name);
    List<Product> findAllByNameContaining(String name);
    Page<Product> findAllByCategory(Pageable pageable, Category category);
    Page<Product> findAllByPriceBetween(Pageable pageable, Double start, Double end);
    Page<Product> findAllByOrderByPrice(Pageable pageable);
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
