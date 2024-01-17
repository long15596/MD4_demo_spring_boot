package com.md4_demo_spring_boot.service;

import com.md4_demo_spring_boot.model.Product;
import com.md4_demo_spring_boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Page<Product> getAllProduct(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
    public Page<Product> getAllByPrice(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByOrderByPrice(pageable);
    }
    public Page<Product> getAllByPriceDesc(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByOrderByPriceDesc(pageable);
    }
    public Page<Product> getAllByPriceBetween(int page, int size, Double star, Double end){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByPriceBetween(pageable, star, end);
    }
    public Page<Product> getAllByName(int page, int size, String search){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByNameContaining(pageable, search);
    }
}
