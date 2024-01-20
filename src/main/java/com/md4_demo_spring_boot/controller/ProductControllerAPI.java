package com.md4_demo_spring_boot.controller;

import com.md4_demo_spring_boot.model.Product;
import com.md4_demo_spring_boot.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductControllerAPI {
    @Autowired
    ProductRepository productRepository;
    @GetMapping
    public ResponseEntity showAll(String name){
        if(name == null){
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity(productRepository.findAllByNameContaining(name), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String err = "";
            for (ObjectError o: bindingResult.getAllErrors()) err += o.getDefaultMessage() + "\n";
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity save(@RequestBody Product product, @PathVariable Long id){
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
